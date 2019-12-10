package top.klw8.alita.validator.beans;

import lombok.Getter;
import lombok.Setter;
import top.klw8.alita.validator.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: PrarmTrimBean
 * @Description: 测试 Trim
 * @date 2019/12/9 18:27
 */
@Getter
@Setter
@GroupNotEmpty(value = "默认组里至少要有两个元素不为空", notEmptyCountMin=2)
@GroupNotEmpty(value = "group2里至少要有两个元素不为空", notEmptyCountMin=2, groupName="group2")
public class PrarmTrimBean {

//    @Required("list 不能为空")
//    @NotEmpty("list 不能为空")
    @GroupItem
    @TrimString
    private List<Object> list;

//    @Required("map 不能为空")
//    @NotEmpty("map 不能为空")
    @GroupItem
    @TrimString
    private Map<String, Object> map;

//    @Required("str 不能为空")
//    @NotEmpty("str 不能为空")
    @TrimString
    @GroupItem("group2")
    private String str;

    @GroupItem("group2")
    @TrimString
    private Object[] array;


}
