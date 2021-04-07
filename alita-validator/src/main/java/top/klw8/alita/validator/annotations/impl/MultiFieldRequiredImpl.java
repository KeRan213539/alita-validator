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
package top.klw8.alita.validator.annotations.impl;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: MultiFieldRequiredImpl
 * @Description: 多属性必传验证器实现
 * @date 2020/1/6 8:39
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.MultiFieldRequired;
import top.klw8.alita.validator.utils.ValidatorUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@ValidatorImpl(validator = MultiFieldRequired.class)
@Slf4j
public class MultiFieldRequiredImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        MultiFieldRequired required = (MultiFieldRequired)annotation;
        String[] values = required.value();
        if(ArrayUtils.isEmpty(values)){
            throw new ValidatorException("500", "MultiFieldRequired中没有配制任何属性!");
        }
        if(object == null){
            String[] value0 = splitValue(values[0]);
            throw new ValidatorException(getFieldCode(value0), getFieldMessage(value0));
        }
        Class cls = object.getClass();
        for(String value : values){
            String[] valueArr = splitValue(value);
            String fieldName = getFieldName(valueArr);
            try {
                Field field = cls.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(object);
                ValidatorUtil.notNull(fieldValue, getFieldCode(valueArr), getFieldMessage(valueArr));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("属性:【{}】不存在或无法访问", fieldName);
                throw new ValidatorException("500", "MultiFieldRequired 验证器使用不正确,请联系开发人员!");
            }
        }
    }

    private String[] splitValue(String str){
        if(str == null){
            str = "";
        }
        String[] result = str.split("::");
        if(result.length != 3){
            String[] resultTemp = new String[3];
            if(result.length == 0){
                resultTemp[0] = "未指定属性名称";
                resultTemp[1] = "500";
                resultTemp[2] = "属性【" + resultTemp[0] + "】未配制验证失败的code和消息";
            }
            if(result.length == 1){
                resultTemp[0] = stringEmpty2Default(result[0], "未指定属性名称");
                resultTemp[1] = "500";
                resultTemp[2] = "属性【" + resultTemp[0] + "】未配制验证失败的code和消息";
            }
            if(result.length == 2){
                resultTemp[0] = stringEmpty2Default(result[0], "未指定属性名称");
                resultTemp[1] = stringEmpty2Default(result[1], "500");
                resultTemp[2] = "属性【" + resultTemp[0] + "】未配制验证失败的code和消息";
            }
            if(result.length >= 3){
                resultTemp[0] = stringEmpty2Default(result[0], "未指定属性名称");
                resultTemp[1] = stringEmpty2Default(result[1], "500");
                resultTemp[2] = stringEmpty2Default(result[2], "属性【" + resultTemp[0] + "】未配制验证失败的code和消息");
            }
            result = resultTemp;
        }
        return result;
    }

    private String stringEmpty2Default(String str, String defaultStr){
        return StringUtils.isBlank(str) ? defaultStr : str;
    }

    private String getFieldName(String[] value){
        return value[0];
    }

    private String getFieldCode(String[] value){
        return value[1];
    }

    private String getFieldMessage(String[] value){
        return value[2];
    }


}
