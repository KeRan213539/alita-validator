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
package top.klw8.alita.validator.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import top.klw8.alita.validator.ValidatorException;


/**
 * @author klw
 * @ClassName: ValidatorUtil
 * @Description: 表单验证器
 * @date 2018年9月17日 13:07:27
 */
public class ValidatorUtil {

    /**
     * @param expression
     * @param statusCode
     * @param message    错误消息
     * @Title: isTrue
     * @Description: 限定是 ture, 不是就不通过
     */
    public static void isTrue(boolean expression, String statusCode, String message) {
        if (!expression) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param object
     * @param statusCode
     * @param message    错误消息
     * @Title: isNull
     * @Description: 限定是 null, 不是就不通过
     */
    public static void isNull(Object object, String statusCode, String message) {
        if (object != null) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param object
     * @param statusCode
     * @param message    错误消息
     * @Title: notNull
     * @Description: 限定不是null, 是null就不通过
     */
    public static void notNull(Object object, String statusCode, String message) {
        if (object == null) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param text
     * @param statusCode
     * @param message    错误消息
     * @Title: hasLength
     * @Description: 限定字符串必须有长度(空格也算有长度), 没有长度则不通过
     */
    public static void hasLength(String text, String statusCode, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param text
     * @param statusCode
     * @param message    错误消息
     * @Title: hasText
     * @Description: 限定字符串必须有字符(空格不算), 没有则不通过
     */
    public static void hasText(String text, String statusCode, String message) {
        if (!StringUtils.hasText(text)) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param textToSearch
     * @param substring
     * @param statusCode
     * @param message      错误消息,为空时将使用  中的默认消息
     * @Title: doesNotContain
     * @Description: 限定第一个参数的字符串中不能包含有第二个参数的字符串, 如果包含则不通过
     */
    public static void doesNotContain(String textToSearch, String substring, String statusCode, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param array
     * @param statusCode
     * @param message    错误消息
     * @Title: notEmpty
     * @Description: 限定数组不能是null或者空(size = 0), 是就不通过
     */
    public static void notEmpty(Object[] array, String statusCode, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param array
     * @param statusCode
     * @param message    错误消息
     * @Title: noNullElements
     * @Description: 限定数组中不能有值为null的元素, 如果有则不通过
     */
    public static void noNullElements(Object[] array, String statusCode, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new ValidatorException(statusCode, message);
                }
            }
        }
    }

    /**
     * @param collection
     * @param statusCode
     * @param message    错误消息
     * @Title: notEmpty
     * @Description: 限定集合不能为null或者空, 如果是null或者空则不通过
     */
    public static void notEmpty(Collection<?> collection, String statusCode, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param map
     * @param statusCode
     * @param message    错误消息
     * @Title: notEmpty
     * @Description: 限定Map不能为null或者空, 如果是null或者空则不通过
     */
    public static void notEmpty(Map<?, ?> map, String statusCode, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param a
     * @param b
     * @param statusCode
     * @param message    错误消息
     * @Title: twoStringConsistent
     * @Description: 验证两个字符串必须一致，否则不通过
     */
    public static void twoStringAreTheSame(String a, String b, String statusCode, String message) {
        hasText(a, statusCode, message);
        hasText(b, statusCode, message);
        if (!a.equals(b)) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param a
     * @param b
     * @param statusCode
     * @param message    错误消息
     * @Title: twoStringConsistent
     * @Description: 验证两个字符串必须不一致，否则不通过
     */
    public static void twoStringNotTheSame(String a, String b, String statusCode, String message) {
        hasText(a, statusCode, message);
        hasText(b, statusCode, message);
        if (a.equals(b)) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param str
     * @return
     * @Title: isMobile
     * @Description: 验证手机号码
     */
    public static void isMobile(String str, String statusCode, String message) {
        hasText(str, statusCode, message);
        Pattern p = Pattern.compile("^1[34578]\\d{9}$");
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param statusCode
     * @param message
     * @param array
     * @Title: atLeastOne
     * @Description: 判断所有参数中至少有一个值
     */
    public static void atLeastOne(String statusCode, String message, String... array) {
        //...参数将带来诸多好处，所以不使用数组类型
        if (array == null || array.length == 0) {
            throw new ValidatorException(statusCode, message);
        }
        for (String s : array) {
            if (StringUtils.hasText(s)) {
                return;
            }
        }
        throw new ValidatorException(statusCode, message);
    }

    /**
     * @param statusCode
     * @param message
     * @param array
     * @Title: allNotEmpty
     * @Description: 所有都不能为空
     */
    public static void allNotEmpty(String statusCode, String message, String... array) {
        if (array == null || array.length == 0) {
            return;
        }
        for (String s : array) {
            if (!StringUtils.hasText(s)) {
                throw new ValidatorException(statusCode, message);
            }
        }
    }

    /**
     * @param statusCode
     * @param message
     * @param array
     * @Title: allNotNull
     * @Description: 所有都不能为null
     */
    public static void allNotNull(String statusCode, String message, Object... array) {
        if (array == null || array.length == 0) {
            return;
        }
        for (Object s : array) {
            if (s == null) {
                throw new ValidatorException(statusCode, message);
            }
        }
    }

    /**
     * @param statusCode
     * @param message
     * @param array
     * @Title: allMustNull
     * @Description: 所有必须是null
     */
    public static void allMustNull(String statusCode, String message, Object... array) {
        if (array == null || array.length == 0) {
            return;
        }
        for (Object s : array) {
            if (s != null) {
                throw new ValidatorException(statusCode, message);
            }
        }
    }

    /**
     * @param statusCode
     * @param message
     * @param lng
     * @param lat
     * @Title: checkLngLatRange
     * @Description: 检查经纬度值是否在经纬度范围内(经度 : - 90 ~ 90, 纬度 : - 180 ~ 180)
     */
    public static void checkLngLatRange(String statusCode, String message, Double lng, Double lat) {
        if (lng < -180D || lng > 180D || lat < -90D || lat > 90D) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param value
     * @param begin
     * @param end
     * @Title: betweenInt
     * @Description: 验证int数字在指定的范围内(不包含上下限)
     */
    public static void betweenInt(Integer value, int begin, int end, String statusCode, String message) {
        ValidatorUtil.notNull(value, statusCode, message);
        if (value.intValue() <= begin || value.intValue() >= end) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @param value
     * @param begin
     * @param end
     * @Title: betweenOrEqualsInt
     * @Description: 验证int数字在指定的范围内(包含上下限)
     */
    public static void betweenOrEqualsInt(Integer value, int begin, int end, String statusCode, String message) {
        ValidatorUtil.notNull(value, statusCode, message);
        if (value.intValue() < begin || value.intValue() > end) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 验证字符串是否是http url
     */
    public static void isHttpUrl(String str, String statusCode, String message) {
        hasText(str, statusCode, message);
        Pattern p = Pattern.compile("(https?)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 检查传入的日期字符串是否是指定格式
     * @Date 2020/1/7 10:50
     * @param: dateStr
     * @param: format
     * @param: statusCode
     * @param: message
     * @return void
     */
    public static void checkDateFormat(String dateStr, String format, String statusCode, String message){
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            formatter.parse(dateStr);
        } catch (Exception e) {
            throw new ValidatorException(statusCode, message);
        }
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 验证身份证号
     * @Date 2020/1/7 11:19
     * @param: str
     * @return boolean
     */
    public static boolean isIdCardNumber(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾


        boolean matches = str.matches(regularExpression);

        //判断第18位校验值
        if (matches) {

            if (str.length() == 18) {
                try {
                    char[] charArray = str.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }
        return matches;
    }


}
