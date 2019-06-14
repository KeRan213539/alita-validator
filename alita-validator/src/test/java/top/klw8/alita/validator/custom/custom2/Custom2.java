package top.klw8.alita.validator.custom.custom2;

import org.springframework.core.annotation.AliasFor;
import top.klw8.alita.validator.ThisIsValidator;

import java.lang.annotation.*;

/**
 * @author klw
 * @ClassName: Custom2
 * @Description: 测试自定义验证器2
 * @date 2019/6/13 17:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Inherited  // 子类可以继承父类的注解
@ThisIsValidator
public @interface Custom2 {

    @AliasFor("validatFailMessage")
    String value() default "自定义2错误";

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
    String validatFailMessage() default "自定义2错误";
}
