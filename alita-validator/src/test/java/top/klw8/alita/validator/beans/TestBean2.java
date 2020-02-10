package top.klw8.alita.validator.beans;

import lombok.Getter;
import lombok.Setter;
import top.klw8.alita.validator.ParamBean;
import top.klw8.alita.validator.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: TestBean2
 * @Description: 综合测试Bean2
 * @date 2020/1/6 16:36
 */
@Getter
@Setter
public class TestBean2 {

    @Required("testBean3不能为空")
    @ParamBean
    private TestBean3 testBean3;

    @Required("testBean3Array不能为空")
    @NotEmpty("testBean3Array不能为空")
    @ParamBean
    private TestBean3[] testBean3Array;

    @Required("testBean3List不能为空")
    @NotEmpty("testBean3List不能为空")
    @ParamBean
    private List<TestBean3> testBean3List;

    @Required("testBean3Map不能为空")
    @NotEmpty("testBean3Map不能为空")
    @ParamBean
    private Map<String, TestBean3> testBean3Map;

}
