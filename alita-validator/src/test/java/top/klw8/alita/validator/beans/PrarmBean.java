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
import top.klw8.alita.validator.annotations.DateRange;
import top.klw8.alita.validator.annotations.NotEmpty;
import top.klw8.alita.validator.annotations.Required;
import top.klw8.alita.validator.custom.custom1.Custom1;
import top.klw8.alita.validator.custom.custom2.Custom2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    @DateRange(beforeNowDays=2,value="Date不在范围")
    private Date date;
    @DateRange(afterNowDays=2,value="localDate不在范围")
    private LocalDate localDate;
    @DateRange(beforeNowDays=2,afterNowDays=2,value="localDateTime不在范围")
    private LocalDateTime localDateTime;

    public PrarmBean(){}
    public PrarmBean(String str1, String str2){
        this.str1 = str1;
        this.str2 = str2;
    }

}
