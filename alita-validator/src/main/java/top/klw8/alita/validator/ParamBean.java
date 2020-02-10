package top.klw8.alita.validator;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: ParamBean
 * @Description: 用于告诉验证器解析器需要解析这属性的java type中的验证器
 * @date 2020/2/10 11:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ParamBean {
}
