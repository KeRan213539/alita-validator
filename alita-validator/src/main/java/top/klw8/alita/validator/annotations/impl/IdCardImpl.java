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

/**
 * 身份证号验证器实现
 * 2020/1/7 11:30
 */

import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.IdCard;
import top.klw8.alita.validator.utils.ValidatorUtil;

import java.lang.annotation.Annotation;

@ValidatorImpl(validator = IdCard.class)
public class IdCardImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        if (object != null) {
            IdCard idCard = (IdCard)annotation;
            String statusCode = idCard.responseStatusCode();
            String message = idCard.validatFailMessage();
            if(object instanceof String && ValidatorUtil.isIdCardNumber((String)object)){
                return;
            }
            throw new ValidatorException(statusCode, message);
        }
    }

}
