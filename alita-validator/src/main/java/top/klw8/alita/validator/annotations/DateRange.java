package top.klw8.alita.validator.annotations;

import org.springframework.core.annotation.AliasFor;
import top.klw8.alita.validator.ThisIsValidator;

import java.lang.annotation.*;
import java.util.Date;

/**
 * @ClassName: DateRange
 * @Description: 日期格式/时间范围验证器,支持旧的方式和 LocalDate/LocalDateTime
 * @author xp
 * @date 2019/11/19 9:15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Inherited  // 子类可以继承父类的注解
@ThisIsValidator
public @interface DateRange {

    @AliasFor("validatFailMessage")
    String value() default "日期不在范围";
    
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
    String validatFailMessage() default "日期不在范围";

    int beforeNowDays() default 0;

    int afterNowDays() default 0;

}
