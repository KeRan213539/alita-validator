package top.klw8.alita.validator.beans;

import lombok.Getter;
import lombok.Setter;
import top.klw8.alita.validator.ParamBean;
import top.klw8.alita.validator.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: TestBean
 * @Description: 综合测试Bean
 * @date 2020/1/6 16:36
 */
@Getter
@Setter
public class TestBean {

    private List<Object> list;

    private Map<String, Object> map;

    @HttpUrl
    private String url;

    @Required("日期不能为空")
    @CheckDateFormat
    private String date;

    @Required("str length 不能为空")
    @StringLength(value = "字符串长度不能大于3", maxLength = 3)
    private String strLength;

    @IdCard
    private String idNo;

    @Required("testBean2不能为空")
    @ParamBean
    private TestBean2 testBean2;

}
