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

import org.springframework.core.annotation.AliasFor;
import top.klw8.alita.validator.ThisIsValidator;

import java.lang.annotation.*;

/**
 * 用于验证trim 后的string的最大、最小长度
 * 2020/2/8 16:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Inherited  //  子类可以继承父类的注解
@ThisIsValidator
public @interface StringLength {

    @AliasFor("validatFailMessage")
    String value() default "字符串长度不符合要求";

    /**
     * 验证失败(不通过)的code
     * @return
     */
    String responseStatusCode() default "500";

    /**
     * 验证失败(不通过)的文字消息,可为空,默认使用ResponseStatusCodeEnum对应的消息
     * @return
     */
    @AliasFor("value")
    String validatFailMessage() default "字符串长度不符合要求";

    /**
     * 最小长度
     */
    int minLength() default 1;

    /**
     * 最大长度
     */
    int maxLength();

}
