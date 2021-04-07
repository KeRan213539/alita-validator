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
import java.util.Collection;
import java.util.Map;

import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.NotEmpty;
import top.klw8.alita.validator.utils.ValidatorUtil;


/**
 * @author klw
 * @ClassName: NotEmptyImpl
 * @Description: 验证String和集合不能为空, 为空则不通过(String判断有字符, 集合判断不为空等)<br />
 * 目前支持: String,数组,集合,Map <br />
 * 【注意】此注解不验证是否为null,如果为null为通过,需要不为null(必传参数)请使用@Required
 * @date 2018年9月17日 13:11:35
 */
@ValidatorImpl(validator = NotEmpty.class)
public class NotEmptyImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        if (object != null) {
            NotEmpty notEmpty = (NotEmpty) annotation;
            String statusCode = notEmpty.responseStatusCode();
            String message = notEmpty.validatFailMessage();
            if (object.getClass().isArray()) {
                ValidatorUtil.notEmpty((Object[]) object, statusCode, message);
            } else if (object instanceof Collection) {
                ValidatorUtil.notEmpty((Collection<?>) object, statusCode, message);
            } else if (object instanceof Map) {
                ValidatorUtil.notEmpty((Map<?, ?>) object, statusCode, message);
            } else if (object instanceof String) {
                ValidatorUtil.hasText((String) object, statusCode, message);
            }
        }
    }

}
