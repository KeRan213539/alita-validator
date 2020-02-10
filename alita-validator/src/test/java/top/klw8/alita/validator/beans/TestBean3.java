package top.klw8.alita.validator.beans;

import lombok.Getter;
import lombok.Setter;
import top.klw8.alita.validator.annotations.Required;
import top.klw8.alita.validator.annotations.StringLength;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: TestBean3
 * @Description: TestBean3
 * @date 2020/2/10 13:33
 */
@Getter
@Setter
public class TestBean3 {

    @Required("str length 不能为空")
    @StringLength(value = "字符串长度不能大于3", maxLength = 3)
    private String strLength;

}
