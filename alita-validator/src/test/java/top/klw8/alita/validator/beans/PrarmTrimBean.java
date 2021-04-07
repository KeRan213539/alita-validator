/*
 * Copyright 2018-2021, ranke (213539@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
