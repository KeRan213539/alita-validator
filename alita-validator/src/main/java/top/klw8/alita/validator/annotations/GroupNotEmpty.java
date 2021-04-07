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
package top.klw8.alita.validator.annotations;

import org.springframework.core.annotation.AliasFor;
import top.klw8.alita.validator.ThisIsValidator;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: GroupNotEmpty
 * @Description: 验证分组中的String和集合至少有几个不能为空(包括null), 为空则不通过(String判断有字符, 集合判断不为空等) <br />
 * 目前支持: String,数组,集合,Map <br />
 * @date 2019/11/5 14:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited  // 子类可以继承父类的注解
@Repeatable(GroupNotEmptys.class)
@ThisIsValidator
public @interface GroupNotEmpty {

    @AliasFor("validatFailMessage")
    String value() default "";

    /**
     * @Title: responseStatusCode
     * @Description: 验证失败(不通过)的code
     * @return
     */
    String responseStatusCode() default "500";

    /**
     * @Title: validatFailMessage
     * @Description: 验证失败(不通过)的文字消息,可为空,默认使用ResponseStatusCodeEnum对应的消息
     * @return
     */
    @AliasFor("value")
    String validatFailMessage() default "";

    /**
     * @author klw(213539@qq.com)
     * @Description: 分组名称
     */
    String groupName() default "defaultGroup";

    /**
     * @author klw(213539@qq.com)
     * @Description: 最少几个元素不能为空
     */
    int notEmptyCountMin() default 1;
}
