/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
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
import top.klw8.alita.validator.annotations.enums.PasswordStrengthLevel;

import java.util.List;
import java.util.Map;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: GroupNotEmptyTestBean
 * @Description: GroupNotEmpty 测试
 * @date 2019/11/5 15:35
 */
@Getter
@Setter
@GroupNotEmpty(value = "默认组里至少要有两个元素不为空", notEmptyCountMin=2)
@GroupNotEmpty(value = "group2里至少要有一个元素不为空", groupName="group2")
@PasswordGroup(level= PasswordStrengthLevel.VERY_STRONG)
public class GroupNotEmptyTestBean {

    @GroupItem
    private String str1;

    @GroupItem
    private List<String> list1;

    @GroupItem
    private Map<String, String> map1;

    @GroupItem("group2")
    private String str2;

    @GroupItem("group2")
    private List<String> list2;

    @GroupItem("group2")
    private Map<String, String> map2;

    @Password(level= PasswordStrengthLevel.VERY_STRONG)
    private String pwd;

    @PasswordGroupItem
    private String pwd1;

    @PasswordGroupItem
    private String pwd2;


}
