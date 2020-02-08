package top.klw8.alita.validator.annotations;

import org.springframework.core.annotation.AliasFor;
import top.klw8.alita.validator.ThisIsValidator;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: StringLength
 * @Description: 用于验证trim 后的string的最大、最小长度
 * @date 2020/2/8 16:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Inherited  //  子类可以继承父类的注解
@ThisIsValidator
public @interface StringLength {

    @AliasFor("validatFailMessage")
    String value() default "字符串长度不符合要求";

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
    String validatFailMessage() default "字符串长度不符合要求";

    /**
     * @author klw(213539@qq.com)
     * @Description: 最小长度
     */
    int minLength() default 1;

    /**
     * @author klw(213539@qq.com)
     * @Description: 最大长度
     */
    int maxLength();

}
