package top.klw8.alita.validator.annotations;

import top.klw8.alita.validator.ThisIsValidator;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: GroupNotEmptys
 * @Description: GroupNotEmpty 的重复容器
 * @date 2019/11/5 17:18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited  // 子类可以继承父类的注解
@ThisIsValidator
public @interface GroupNotEmptys {

    GroupNotEmpty[] value();

}
