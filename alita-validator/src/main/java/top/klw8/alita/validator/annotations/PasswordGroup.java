package top.klw8.alita.validator.annotations;

import org.springframework.core.annotation.AliasFor;
import top.klw8.alita.validator.ThisIsValidator;
import top.klw8.alita.validator.annotations.enums.PasswordStrengthLevel;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: PasswordGroup
 * @Description: 用户密码组, 用户验证组中的密码是否一致及是否符合规则
 * @date 2019/11/6 9:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited  // 子类可以继承父类的注解
@ThisIsValidator
public @interface PasswordGroup {

    /**
     * @Title: responseStatusCode
     * @Description: 验证失败(不通过)的code
     * @return
     */
    String responseStatusCode() default "500";

    /**
     * @author klw(213539@qq.com)
     * @Description: 最小长度
     */
    int minLength() default 6;

    /**
     * @author klw(213539@qq.com)
     * @Description: 最大长度
     */
    int maxLength() default 16;

    /**
     * @author klw(213539@qq.com)
     * @Description: 密码强度级别,默认 复杂
     */
    PasswordStrengthLevel level() default PasswordStrengthLevel.STRONG;

}
