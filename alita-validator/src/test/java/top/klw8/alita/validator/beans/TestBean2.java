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
