package top.klw8.alita.validator.beans;

import lombok.Getter;
import lombok.Setter;
import top.klw8.alita.validator.annotations.NotEmpty;
import top.klw8.alita.validator.annotations.Required;
import top.klw8.alita.validator.custom.custom1.Custom1;
import top.klw8.alita.validator.custom.custom2.Custom2;

/**
 * @author klw
 * @ClassName: PrarmBean
 * @Description: 测试用实体, 用做参数
 * @date 2019/6/12 17:17
 */
@Getter
@Setter
public class PrarmBean {

    @Custom1
    private String str1;

    @Required("参数2必须有值")
    @NotEmpty("参数2不能为空字符串")
    @Custom2
    private String str2;

    public PrarmBean(String str1, String str2){
        this.str1 = str1;
        this.str2 = str2;
    }

}
