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
import java.lang.reflect.Field;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.Password;
import top.klw8.alita.validator.annotations.PasswordGroup;
import top.klw8.alita.validator.annotations.enums.PasswordStrengthLevel;
import top.klw8.alita.validator.utils.GroupUtil;
import top.klw8.alita.validator.utils.PasswordCheckUtil;

/**
 * 用户密码验证器实现
 * 2019年1月30日 下午6:18:13
 */
@ValidatorImpl(validator = {PasswordGroup.class, Password.class})
@Slf4j
public class PasswordImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        String pwd;
        int annotationMinLength;
        int annotationMaxLength;
        String statusCode;
        PasswordStrengthLevel level;
        if(annotation instanceof Password) {
            Password pwdan = (Password) annotation;
            annotationMinLength = pwdan.minLength();
            annotationMaxLength = pwdan.maxLength();
            statusCode = pwdan.responseStatusCode();
            if (object == null) {
                throw new ValidatorException(statusCode, "请输入密码!");
            }
            pwd = (String) object;
            level = pwdan.level();
        } else {
            PasswordGroup pwdan = (PasswordGroup) annotation;
            statusCode = pwdan.responseStatusCode();
            if (object == null) {
                throw new ValidatorException(statusCode, "请输入密码!");
            }
            List<Field> pwdItemList = GroupUtil.getAllPasswordGroupItem(object.getClass());
            if(CollectionUtils.isEmpty(pwdItemList) || pwdItemList.size() < 2){
                throw new ValidatorException(statusCode, "【" + object.getClass().getName() + "】使用了 PasswordGroup, 但是未配制PasswordGroupItem 或者只配制了一个!");
            }
            Field pwdItem1 = pwdItemList.get(0);
            pwdItem1.setAccessible(true);
            Object pwdItem1Vale;
            try {
                pwdItem1Vale = pwdItem1.get(object);
                if (pwdItem1Vale == null) {
                    throw new ValidatorException(statusCode, "请输入密码!");
                }
            } catch (IllegalAccessException e) {
                log.error("反射获取字段值出错", e);
                throw new ValidatorException(statusCode, "反射获取字段值出错");
            }
            pwd = (String) pwdItem1Vale;
            for(int i = 1; i < pwdItemList.size(); i++){
                Field pwdItem = pwdItemList.get(i);
                pwdItem.setAccessible(true);
                Object pwdItemVale;
                try {
                    pwdItemVale = pwdItem.get(object);
                    if (pwdItemVale == null) {
                        throw new ValidatorException(statusCode, "请输入密码!");
                    }
                } catch (IllegalAccessException e) {
                    log.error("反射获取字段值出错", e);
                    throw new ValidatorException(statusCode, "反射获取字段值出错");
                }
                String pwdOther = (String) pwdItemVale;
                if(!pwd.equals(pwdOther)){
                    throw new ValidatorException(statusCode, "密码不一致!");
                }
            }
            annotationMinLength = pwdan.minLength();
            annotationMaxLength = pwdan.maxLength();
            level = pwdan.level();
        }
        if (!PasswordCheckUtil.checkPasswordLength(pwd, annotationMinLength, annotationMaxLength)) {
            throw new ValidatorException(statusCode, "密码长度必须大于" + annotationMinLength + "小于" + annotationMaxLength);
        }

        switch(level){
            case NUMBER:
                if(!PasswordCheckUtil.checkOnlyContainDigit(pwd)){
                    throw new ValidatorException(statusCode, "密码必须是纯数字");
                }
                break;
            case VERY_STRONG:
                if(PasswordCheckUtil.checkLateralKeyboardSite(pwd, 3, false)){
                    throw new ValidatorException(statusCode, "密码中键盘连续字符不能超过3个,如: asd,qaz");
                }
                if(PasswordCheckUtil.checkKeyboardSlantSite(pwd, 3, false)){
                    throw new ValidatorException(statusCode, "密码中键盘连续字符不能超过3个,如: asd,qaz 等");
                }
                if(PasswordCheckUtil.checkSequentialChars(pwd, 3, false)){
                    throw new ValidatorException(statusCode, "密码中字母表连续字母不能超过3个, 如: abc, def 等");
                }
                if(PasswordCheckUtil.checkSequentialSameChars(pwd, 3)){
                    throw new ValidatorException(statusCode, "密码中相同字符不能能超过3个, 如: aaa, @@@ 等");
                }
            case STRONG:
                if(!PasswordCheckUtil.checkContainLowerCase(pwd)){
                    throw new ValidatorException(statusCode, "密码中必须有至少一个小写字母");
                }
                if(!PasswordCheckUtil.checkContainUpperCase(pwd)){
                    throw new ValidatorException(statusCode, "密码中必须有至少一个大写字母");
                }
                if(!PasswordCheckUtil.checkContainSpecialChar(pwd)){
                    throw new ValidatorException(statusCode, "密码中必须有至少一个特殊符号");
                }
            case EASY:
                if(!PasswordCheckUtil.checkContainDigit(pwd)){
                    throw new ValidatorException(statusCode, "密码中必须有至少一个数字");
                }
                if(!PasswordCheckUtil.checkContainCase(pwd)){
                    throw new ValidatorException(statusCode, "密码中必须有至少一个字母");
                }
                break;
            default:
                throw new ValidatorException(statusCode, "不支持的密码强度级别");
        }
    }

}
