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
import top.klw8.alita.validator.annotations.MobilePhoneNumber;
import top.klw8.alita.validator.utils.ValidateUtil;


/**
 * 验证手机号格式
 * 2018-11-22 16:58:22
 */
@ValidatorImpl(validator = MobilePhoneNumber.class)
public class MobilePhoneNumberImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
	if (object != null) {
	    // 不为null才验证
	    MobilePhoneNumber mobilePhoneNumber = (MobilePhoneNumber) annotation;
	    if (!ValidateUtil.isMobileNO((String) object)) {
		throw new ValidatorException(mobilePhoneNumber.responseStatusCode(),
			mobilePhoneNumber.validatFailMessage());
	    }
	}
    }

}
