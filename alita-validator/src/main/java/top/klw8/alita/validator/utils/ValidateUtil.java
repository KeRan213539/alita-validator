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
package top.klw8.alita.validator.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

/**
 * 一些常用效验
 * 2018-11-22 09:48:57
 */
public class ValidateUtil {

    /**
     * @param str
     * @param regex
     * @return
     * 根据正则表达式效验字符串
     */
    public static boolean checkStrByRegex(String str, String regex) {
        return str.matches(regex);
    }

    /**
     * @param ip
     * @return
     * 检查IP格式是否正确
     */
    public static boolean checkIpFormatting(String ip) {
        String regex = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
        return ip.matches(regex);
    }

    /**
     * @param dateStr
     * @param formatStr
     * @return
     * 检查传入的字符串是否是指定的日期格式
     */
    public static boolean checkDateFormatting(String dateStr, String formatStr) {
        DateFormat formatter = new SimpleDateFormat(formatStr);
        try {
            formatter.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param lng
     * @param lat
     * 检查经纬度值是否在经纬度范围内(经度 : - 90 ~ 90, 纬度 : - 180 ~ 180)
     */
    public static boolean checkLatLngRange(Double lat, Double lng) {
        if (lat == null || lng == null) {
            return false;
        }
        if (lat < -180D || lat > 180D || lng < -90D || lng > 90D) {
            return false;
        }
        return true;
    }

    /**
     * @param lng
     * @return
     * 检查经度范围
     */
    public static boolean checkLongitudeRange(Double lng) {
        if (lng == null) {
            return false;
        }
        if (lng < -90D || lng > 90D) {
            return false;
        }
        return true;
    }

    /**
     * @param lat
     * @return
     * 检查纬度范围
     */
    public static boolean checkLatitudeRange(Double lat) {
        if (lat == null) {
            return false;
        }
        if (lat < -180D || lat > 180D) {
            return false;
        }
        return true;
    }


    /**
     * @param mobileNo
     * @return
     * 验证手机号格式是否正确
     */
    public static boolean isMobileNO(String mobileNo) {
        String telRegex = "^1[345789]\\d{9}$";
        return checkStringByRegx(mobileNo, telRegex);
    }

    /**
     * @param idCardNo
     * @return
     * 检查是否是身份证号
     */
    public static boolean isChinaIdCardNo(String idCardNo) {
        String idCardRegex = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
        return checkStringByRegx(idCardNo, idCardRegex);
    }

    /**
     * @param str
     * @param regx
     * @return
     * 根据正则表达式验证字符串
     */
    private static boolean checkStringByRegx(String str, String regx) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return str.matches(regx);
    }

    /**
     * @param dateStr
     * @return
     * 检查日期格式是否是 yyyyMMddHHmmss
     */
    public static boolean checkDateFormatting_yyyyMMddHHmmss(String dateStr) {
        try {
            Long.parseLong(dateStr);
        } catch (Exception e) {
            return false;
        }
        return ValidateUtil.checkDateFormatting(dateStr, "yyyyMMddHHmmss");
    }
}
