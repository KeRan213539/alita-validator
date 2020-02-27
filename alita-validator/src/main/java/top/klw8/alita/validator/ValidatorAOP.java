package top.klw8.alita.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import top.klw8.alita.validator.annotations.TrimString;
import top.klw8.alita.validator.utils.TrimStringUtil;
import top.klw8.alita.validator.utils.ValidatorUtil;

/**
 * @author klw
 * @ClassName: ValidatorAOP
 * @Description: 处理验证器的切面
 * @date 2018年9月17日 下午4:46:03
 */
@Aspect
public class ValidatorAOP {

    private static Logger logger = LoggerFactory.getLogger(ValidatorAOP.class);

    /**
     * @author klw
     * @Fields validatorImplsMap : 验证器注解对应的实现的缓存
     */
    private volatile static Map<String, Class<? extends IAnnotationsValidator>> validatorImplsCacheMap = null;

    @Autowired
    private IResponseMsgGenerator respGenerator;

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(top.klw8.alita.validator.UseValidator)")
    public void validatorAOPAdvise() {
    }

    @Around("validatorAOPAdvise()") // 也可以将上面 @Pointcut 的内容直接放这里
    public Object doAdvise(ProceedingJoinPoint pjp) {

        if (validatorImplsCacheMap == null) {
            synchronized (this) {
                if (validatorImplsCacheMap == null) {
                    validatorImplsCacheMap = new ConcurrentHashMap<>();
                    Map<String, Object> validatorImplsMap = applicationContext.getBeansWithAnnotation(ValidatorImpl.class);
                    if (MapUtils.isNotEmpty(validatorImplsMap)) {
                        for (Entry<String, Object> entry : validatorImplsMap.entrySet()) {
                            IAnnotationsValidator validatorImpl = (IAnnotationsValidator) entry.getValue();
                            ValidatorImpl validatorImplAnnotation = validatorImpl.getClass().getAnnotation(ValidatorImpl.class);
                            for (Class<? extends Annotation> vali : validatorImplAnnotation.validator()) {
                                Class<? extends IAnnotationsValidator> impl = validatorImplsCacheMap.get(vali.getName());
                                if (impl != null) {
                                    logger.warn("【警告】验证器【" + vali.getName() + "】有多个实现,【" + impl.getName() + "】被替换为【" + validatorImpl.getClass().getName() + "】");
                                }
                                validatorImplsCacheMap.put(vali.getName(), validatorImpl.getClass());
                            }
                        }
                    }
                }
            }
        }

        // 获取被拦截的方法的参数
        Object[] args = pjp.getArgs();
        // 遍历该方法的所有参数
        try{
            Signature signature = pjp.getSignature();
            Method targetMethod = ((MethodSignature)signature).getMethod();
            Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());
            Annotation[][] argAnns = realMethod.getParameterAnnotations();
            if (args != null && args.length > 0) {
                try {
                    for (int i = 0; i < args.length; i++) {
                        Object arg = args[i];
                        Object rt = checkAnnotationAndDoValidat(argAnns[i], arg);
                        if (rt != null) {
                            args[i] = rt;
                        }
                        if(arg != null) {
                            // 把类注解拿出来看看有没有类级验证器,有就处理
                            Object rt3 = checkAnnotationAndDoValidat(arg.getClass().getAnnotations(), arg);
                            if (rt3 != null) {
                                args[i] = rt;
                            }
                            // 处理属性上的注解
                            this.processField(arg, 0);
                        }
                    }
                } catch (ValidatorException ex) {
                    String errMsg;
                    if (StringUtils.isBlank(ex.getErrorMsg())) {
                        errMsg = "【该验证器注解没有设置错误消息】";
                    } else {
                        errMsg = ex.getErrorMsg();
                    }
                    return respGenerator
                            .generatorResponse(ex.getStatusCode(), errMsg, ex);
                }
            }

        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Object ret;
        try {
            ret = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException("AOP Point Cut ValidatorAOP Throw Exception :" + e.getMessage(), e);
        }
        return ret;
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 处理方法参数中的属性
     * @Date 2020/2/10 12:48
     * @param: arg
     * @return void
     */
    private void processField(Object arg, int processCount) throws IllegalAccessException {
        if(processCount >= 10){
            logger.warn("使用验证器的Bean的深度已超过10层,剩下的验证器将被忽略!请检查Bean中是否有循环引用!");
            return;
        }
        processCount++;
        // 获取所有字段
        List<Field> allFields = getAllFields(null, arg.getClass());
        for (Field field : allFields) {
            field.setAccessible(true);
            Object fieldValue = field.get(arg);

            Object rt2 = checkAnnotationAndDoValidat(field.getAnnotations(), fieldValue);
            if (rt2 != null) {
                field.set(arg, rt2);
            }
            // 如果属性上有ParamBean注解,需要处理属性的java type中的验证器
            if(field.isAnnotationPresent(ParamBean.class)){
                // 处理常用集合
                if (fieldValue != null && fieldValue.getClass().isArray()) {
                    Object[] array = (Object[])fieldValue;
                    for(Object arrayItem : array){
                        processField(arrayItem, processCount);
                    }
                } else if (fieldValue != null && fieldValue instanceof Collection) {
                    Collection<?> collection = (Collection<?>) fieldValue;
                    for(Object item : collection){
                        processField(item, processCount);
                    }
                } else if (fieldValue != null && fieldValue instanceof Map) {
                    Map<?, ?> map = (Map<?, ?>) fieldValue;
                    for(Object item : map.values()){
                        processField(item, processCount);
                    }
                } else {
                    processField(fieldValue, processCount);
                }
            }
        }
    }

    /**
     * @author klw
     * @Description: 检查是否有验证器注解,有就执行验证
     * @Date 2019/6/12 16:01
     * @Param annotations 方法参数,类属性的注解
     * @Param fieldValue 方法参数,类属性的值
     * @return Object 验证不通过的自定义响应
     */
    private Object checkAnnotationAndDoValidat(Annotation[] annotations, Object fieldValue) throws ValidatorException{
        if (annotations != null && annotations.length > 0) {
            // 遍历注解,找到验证器的注解
            for (Annotation fieldAnn : annotations) {
                // 处理 trim 注解
                if(fieldAnn instanceof TrimString){
                    return TrimStringUtil.trim(fieldValue);
                }
                // 检查该注解是否有@ThisIsValidator,有就说明是验证器
                if (fieldAnn.annotationType().getAnnotation(ThisIsValidator.class) != null) {
                    // 通过spring拿到验证器进行验证,先拿验证器的springBeanName
                    Class<?> validatorSpringBeanClass = validatorImplsCacheMap
                            .get(fieldAnn.annotationType().getName());
                    // 处理 @AliasFor
                    fieldAnn = AnnotationUtils.synthesizeAnnotation(fieldAnn, null);
                    // 名字有值,从spring容器中拿对应的验证器
                    IAnnotationsValidator annotationsValidator = (IAnnotationsValidator) applicationContext
                            .getBean(validatorSpringBeanClass);
                    if (annotationsValidator != null) {
                        // 验证器不为空,调用验证器
                        annotationsValidator.doValidator(fieldValue,
                                    fieldAnn);
                    } else {
                        logger.error(
                                "验证器【{}】指定的验证器实现没有找到,请验证器开发人员检查是否在 top.klw8.alita.validator.cfg.ValidatorsConfig 中配制了该实现",
                                fieldAnn.annotationType());
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param fieldList
     * @param classz
     * @return
     * @Title: getAllFields
     * @Description: 递归获取该类的所有属性包括父类的爷爷类的...祖宗类的
     */
    private List<Field> getAllFields(List<Field> fieldList, Class<?> classz) {
        if (classz == null) {
            return fieldList;
        }
        if (fieldList == null) {
            fieldList = new ArrayList<>(Arrays.asList(classz.getDeclaredFields()));  // 获得该类的所有字段,但不包括父类的
            /**
             * 巨坑巨坑!!!
             * Arrays.asList() 返回的是 Arrays的内部类ArrayList， 而不是java.util.ArrayList。
             * Arrays的内部类ArrayList和java.util.ArrayList都是继承AbstractList，
             * remove、add等方法AbstractList中是默认throw UnsupportedOperationException而且不作任何操作。
             * java.util.ArrayList重写了这些方法而Arrays的内部类ArrayList没有重写，所以会抛出异常。
             * 解决办法就是把 Arrays.asList() 返回的东西再丢到 new java.util.ArrayList() 里面,重新生成一个java.util.ArrayList
             */
        } else {
            CollectionUtils.addAll(fieldList, classz.getDeclaredFields()); // 获得该类的所有字段,但不包括父类的
        }
        return getAllFields(fieldList, classz.getSuperclass());
    }

}
