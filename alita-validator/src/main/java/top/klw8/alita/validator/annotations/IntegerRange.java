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

/**
 * 验证int参数是否在指定范围
 * 2019年1月26日 下午12:34:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Inherited  // 子类可以继承父类的注解
@ThisIsValidator
public @interface IntegerRange {

    /**
     * 验证失败(不通过)的code
     * @return
     */
    String responseStatusCode() default "500";
    
    /**
     * 验证失败(不通过)的文字消息,可为空,默认使用ResponseStatusCodeEnum对应的消息
     * @return
     */
    String validatFailMessage();
    
    int min();
    
    int max();
    
}
