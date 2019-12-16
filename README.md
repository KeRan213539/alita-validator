# Alita Validator

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

从Alita项目中抽取出来的注解验证器, 用于对方法参数做验证,如常用的非空,手机号格式,邮箱格式,数字范围等. 通常用于HTTP项目(包括Restful)中客户端提交的参数的验证

大家可能会问,spring MVC支持验证注解,如常用的hibernate-validator,为什么要自己实现一套呢?
最近做一个APP的服务端接口,项目中有自己的业务返回码.spring MVC支持的注解验证器无法设置验证不通过的时候的返回码,各种不方便,所以思前想后还是自己实现了一套

## 主要功能
* 基于Spring Boot 2.x
* **验证JAVA方法的参数是否符合要求**：验证器是基于注解的,可用于验证任何的JAVA方法的参数
* **自定义错误码**：使用注解的时候可以自定义验证失败时的错误码
* **自定义错误消息**: 使用注解的时候可以自定义验证失败的消息,该消息为一个字符串,如果做国际化,可以在错误消息中放入国际化资源标识,在responseMsgGenerator 处理该资源标识
* **返回类型自定**：通过实现错误响应接口,可以实现验证失败被调用的方法返回的内容自定,而不是抛出异常. 例如WEB项目中可以返回一个项目定义的Response格式的对象.
* **各种验证方式**：目前提供少量的验证器,如必传,非空,密码(复杂度,长度等),手机号格式等.后面会慢慢增加更多的验证器,当然大家也可以提PR
* **方法参数使用验证器(0.2新增)**: 现在可以在方法参数上使用验证器,并且如果该参数是个Java Bean, 并且属性中使用了验证器也会进行验证
* **自定义验证器扩展(0.2新增)**: 通过在配制项"alita.validator.custom.packages" 配制自定义的验证器实现的包路径,可扫描自定义的验证器.多个包路径逗号分隔
* **@Trimstring 注解(0.3新增)**: 用于去除字符串/集合中的字符串中的前后空格(不是验证器),支持处理集合中的字符串
* **日期范围验证(0.3新增)**: 可以验证日期参数的开始结束返回,或者指定日期之后, 指定日期之前
* **@GroupNotEmpty注解(0.3新增)**: 用于验证一组指定的属性,其中至少几个非空, 配合@GroupItem(用于标注组中的元素), 如果一个类中有多组,需要配合@GroupNotEmptys 使用,具体使用方式参考


## 更新日志
### **0.3**
* 增加 @Trimstring 注解,用于去除字符串/集合中的字符串中的前后空格(不是验证器),支持处理集合中的字符串
* APO里增加对处理类注解的支持,现在可以定义并处理类级注解,如新增的 @GroupNotEmpty
* 新增验处理器是否实现了验证器接口的校验,扫描验证器时过滤指定包中的非验证器类
* 新增日期范围验证器@DateRange,可以验证日期参数的开始结束返回,或者指定日期之后, 指定日期之前


## 未来规划

* 根据自用发现的需求增加功能



## 开始使用

**maven引入**:

```
<dependency>
    <groupId>top.klw8.alita</groupId>
    <artifactId>alita-validator</artifactId>
    <version>0.3</version>
</dependency>
```

**开发一个responseMsgGenerator**:

 responseMsgGenerator 作为验证错误的响应生成器,用于生成当验证不通过时被调用方法的返回的值,例如WBE项目用于验证客户端提交的参数,可以返回一个统一的响应结构.

实现top.klw8.alita.validator.IResponseMsgGenerator接口,根据传入的自定义错误码和自定义错误消息生成对应的返回值



**开启验证器**:

在配制类(标注了@Configuration的类)或者spring boot 启动器(标注了@SpringBootApplication的类)的类中加上类注解: 

```
@EnableValidator(responseMsgGenerator = ValidatorResponseGenerator.class)
```

其中 responseMsgGenerator 为上面开发的 responseMsgGenerator

**使用验证器**:

在需要验证的方法上加上方法注解 top.klw8.alita.validator.UseValidator:

```
@UseValidator
public CustomResult xxx(Demo demo)
```

在Demo类的属性上加上具体验证器:

```
@Required(validatFailMessage = "姓名不能为空", responseStatusCode="5008")
private String fullName;
```





## 开发验证器
验证器注解可以在使用的项目中单独开发,通过配置项"alita.validator.custom.packages" 指定验证器实现所在包就行,多个包路径用逗号分隔,验证器注解在哪个包无所谓.当然也欢迎各种PR~(如果要PR,则验证器注解必须放在 "top.klw8.alita.validator.annotations"包下,实现必须放在"top.klw8.alita.validator.annotations.impl"包下)

**定义验证器注解**

验证器注解可以放在任意包中

例如开发一个非空验证器(当值不为null的时候判断是否空,为null不处理):

```
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited
@ThisIsValidator
public @interface NotEmpty {

    @AliasFor("validatFailMessage")
    String value() default "";
    
    /**
     * @Title: responseStatusCode
     * @Description: 验证失败(不通过)的code
     * @return
     */
    String responseStatusCode() default "500";
    
    /**
     * @Title: validatFailMessage
     * @Description: 验证失败(不通过)的文字消息,可为空,默认使用ResponseStatusCodeEnum对应的消息
     * @return
     */
    @AliasFor("value")
    String validatFailMessage() default "";
    
    
    // 更多参数...
}
```

上面例子中的value,responseStatusCode和 validatFailMessage 为每个验证器必须有的参数,当然还可以增加更多的参数,在验证器实现解析

 **开发验证器实现类**

验证器实现可以放在任意包中,然后在配制中指定包路径

需要实现  top.klw8.alita.validator.IAnnotationsValidator 接口

```
@ValidatorImpl(validator = NotEmpty.class)  //标注该实现是哪个验证器的实现,如果是多个验证器共有的实现则可以validator = {NotEmpty.class, Xxx.class}
public class NotEmptyImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws Exception {
	NotEmpty notEmpty = (NotEmpty) annotation;
	String statusCode = notEmpty.responseStatusCode();
	String message = notEmpty.validatFailMessage();
	if (object != null) {
	    if (object.getClass().isArray()) {
		ValidatorUtil.notEmpty((Object[]) object, statusCode, message);
	    } else if (object instanceof Collection) {
		ValidatorUtil.notEmpty((Collection<?>) object, statusCode, message);
	    } else if (object instanceof Map) {
		ValidatorUtil.notEmpty((Map<?, ?>) object, statusCode, message);
	    } else if (object instanceof String) {
		ValidatorUtil.hasText((String) object, statusCode, message);
	    }
	}
    }

}
```

验证器实现不返回任值,验证不通过时抛出 top.klw8.alita.validator.ValidatorException 异常即可 

