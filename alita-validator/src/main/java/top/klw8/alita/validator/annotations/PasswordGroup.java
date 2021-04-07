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

import top.klw8.alita.validator.ThisIsValidator;
import top.klw8.alita.validator.annotations.enums.PasswordStrengthLevel;

import java.lang.annotation.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: PasswordGroup
 * @Description: 用户密码组, 用户验证组中的密码是否一致及是否符合规则
 * @date 2019/11/6 9:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited  // 子类可以继承父类的注解
@ThisIsValidator
public @interface PasswordGroup {

    /**
     * @Title: responseStatusCode
     * @Description: 验证失败(不通过)的code
     * @return
     */
    String responseStatusCode() default "500";

    /**
     * @author klw(213539@qq.com)
     * @Description: 最小长度
     */
    int minLength() default 6;

    /**
     * @author klw(213539@qq.com)
     * @Description: 最大长度
     */
    int maxLength() default 16;

    /**
     * @author klw(213539@qq.com)
     * @Description: 密码强度级别,默认 复杂
     */
    PasswordStrengthLevel level() default PasswordStrengthLevel.STRONG;

}
