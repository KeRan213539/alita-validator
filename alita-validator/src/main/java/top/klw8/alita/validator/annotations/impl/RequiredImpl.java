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
package top.klw8.alita.validator.annotations.impl;

import java.lang.annotation.Annotation;

import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.Required;
import top.klw8.alita.validator.utils.ValidatorUtil;


/**
 * @author klw
 * @ClassName: RequiredImpl
 * @Description: 必传参数注解, 使用该注解的参数为必传参数.此注解仅验证参数是否为null, 不管其他, 如需要验证格式等请配合其他注解一起使用
 * @date 2018年9月17日 13:11:39
 */
@ValidatorImpl(validator = Required.class)
public class RequiredImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        Required required = (Required) annotation;
        String statusCode = required.responseStatusCode();
        String message = required.validatFailMessage();
        ValidatorUtil.notNull(object, statusCode, message);
    }

}
