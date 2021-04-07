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
package top.klw8.alita.validator.annotations;

import top.klw8.alita.validator.ThisIsValidator;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: MultiFieldRequired
 * @Description: 多属性必传验证器注解,用于方法参数,指定作为参数的bean中的多个属性必传 <br />
 * 格式(双冒号隔开): 属性名::验证失败code::验证失败消息,例: <br />
 * @MultiFieldRequired({"name::500001::姓名为必传参数","age::500002::年龄为必传参数"})
 * @date 2020/1/6 8:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Documented
@ThisIsValidator
public @interface MultiFieldRequired {

    String[] value();

}
