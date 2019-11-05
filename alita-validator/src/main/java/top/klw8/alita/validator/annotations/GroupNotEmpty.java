package top.klw8.alita.validator.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: GroupNotEmpty
 * @Description: 验证分组中的String和集合至少有几个不能为空(包括null), 为空则不通过(String判断有字符, 集合判断不为空等) <br />
 * 目前支持: String,数组,集合,Map <br />
 * @date 2019/11/5 14:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited  // 子类可以继承父类的注解
@Repeatable(GroupNotEmptys.class)
public @interface GroupNotEmpty {

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

    /**
     * @author klw(213539@qq.com)
     * @Description: 分组名称
     */
    String groupName() default "defaultGroup";

    /**
     * @author klw(213539@qq.com)
     * @Description: 最少几个元素不能为空
     */
    int notEmptyCountMin() default 1;
}
