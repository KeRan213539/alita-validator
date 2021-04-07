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
