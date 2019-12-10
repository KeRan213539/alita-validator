package top.klw8.alita.validator.annotations;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: TrimString
 * @Description: 这不是一个验证器,该注解用于
 * 自动去除字符串的前后空格,如果被注解的是集合(目前支持 Collection, Map, 字符串数组)
 * @date 2019/12/9 17:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Inherited  // 子类可以继承父类的注解
public @interface TrimString {
}
