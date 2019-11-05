package top.klw8.alita.validator.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: GroupItem
 * @Description: 分组元素,该注解不是验证器注解,需要配合 @GroupNotEmpty 或者其他分组类验证器使用,单独使用无效
 * @date 2019/11/5 14:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited  // 子类可以继承父类的注解
public @interface GroupItem {

    /**
     * @author klw(213539@qq.com)
     * @Description: 分组名称
     */
    String value() default "defaultGroup";

}
