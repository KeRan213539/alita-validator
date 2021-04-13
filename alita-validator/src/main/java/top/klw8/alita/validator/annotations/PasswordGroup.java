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
import top.klw8.alita.validator.annotations.enums.PasswordStrengthLevel;

import java.lang.annotation.*;

/**
 * 用户密码组, 用户验证组中的密码是否一致及是否符合规则
 * 2019/11/6 9:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited  // 子类可以继承父类的注解
@ThisIsValidator
public @interface PasswordGroup {

    /**
     * 验证失败(不通过)的code
     * @return
     */
    String responseStatusCode() default "500";

    /**
     * 最小长度
     */
    int minLength() default 6;

    /**
     * 最大长度
     */
    int maxLength() default 16;

    /**
     * 密码强度级别,默认 复杂
     */
    PasswordStrengthLevel level() default PasswordStrengthLevel.STRONG;

}
