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
package top.klw8.alita.validator;

import java.lang.annotation.Annotation;
import java.text.ParseException;

/**
 * @ClassName: IAnnotationsValidator
 * @Description: 注解验证器的验证接口,每种注解验证的实现需要实现该接口
 * @author klw
 * @date 2018年9月17日 13:07:53
 */
public interface IAnnotationsValidator {

    void doValidator(Object object, Annotation annotation) throws ValidatorException;
    
}
