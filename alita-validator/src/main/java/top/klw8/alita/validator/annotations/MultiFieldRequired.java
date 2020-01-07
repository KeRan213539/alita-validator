package top.klw8.alita.validator.annotations;

import top.klw8.alita.validator.ThisIsValidator;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: MultiFieldRequired
 * @Description: 多属性必传验证器注解,用于方法参数,指定作为参数的bean中的多个属性必传 <br />
 * 格式(双冒号隔开): 属性名::验证失败code::验证失败消息,例: <br />
 * @MultiFieldRequired({"name::500001::姓名为必传参数","age::500002::年龄为必传参数"})
 * @date 2020/1/6 8:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Documented
@ThisIsValidator
public @interface MultiFieldRequired {

    String[] value();

}
