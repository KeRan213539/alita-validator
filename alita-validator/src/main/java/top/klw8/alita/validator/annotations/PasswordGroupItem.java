package top.klw8.alita.validator.annotations;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: PasswordGroupItem
 * @Description: PasswordGroup 中的密码
 * @date 2019/11/6 10:00
 */

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited  // 子类可以继承父类的注解
public @interface PasswordGroupItem {
}
