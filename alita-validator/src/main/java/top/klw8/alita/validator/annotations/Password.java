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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import top.klw8.alita.validator.ThisIsValidator;
import top.klw8.alita.validator.annotations.enums.PasswordStrengthLevel;

/**
 * 用户密码验证器,验证用户的密码是否符合要求
 * 1. 可配制最小密码长度(默认6)
 * 2. 可配制最大密码长度(默认16)
 * 3. TODO 可配制密码类型(只能数字; 只能字母; 混合;)
 * 4. TODO 可配制混合下的复杂度(必须包含字母; 必须包含大写字母; 必须包含特殊符号;)
 * 2019年1月30日 下午6:12:14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Inherited  // 子类可以继承父类的注解
@ThisIsValidator
public @interface Password {

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
