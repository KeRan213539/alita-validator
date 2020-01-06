package top.klw8.alita.validator.beans;

import lombok.Getter;
import lombok.Setter;
import top.klw8.alita.validator.annotations.GroupItem;
import top.klw8.alita.validator.annotations.HttpUrl;
import top.klw8.alita.validator.annotations.TrimString;

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

    private Object[] array;

}
