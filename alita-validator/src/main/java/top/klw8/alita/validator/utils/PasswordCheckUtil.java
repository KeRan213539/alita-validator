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

import org.apache.commons.lang3.StringUtils;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: PasswordCheckUtil
 * @Description: 密码检测工具类
 * @date 2019/11/6 11:12
 */
public class PasswordCheckUtil {

    private static String[] KEYBOARD_SLOPE_ARR = {
            "!qaz", "1qaz", "@wsx", "2wsx", "#edc", "3edc", "$rfv", "4rfv", "%tgb", "5tgb",
            "^yhn", "6yhn", "&ujm", "7ujm", "*ik,", "8ik,", "(ol.", "9ol.", ")p;/", "0p;/",
            "+[;.", "=[;.", "_pl,", "-pl,", ")okm", "0okm", "(ijn", "9ijn", "*uhb", "8uhb",
            "&ygv", "7ygv", "^tfc", "6tfc", "%rdx", "5rdx", "$esz", "4esz"
    };
    private static String[] KEYBOARD_HORIZONTAL_ARR = {
            "01234567890-=",
            "!@#$%^&*()_+",
            "qwertyuiop[]",
            "QWERTYUIOP{}",
            "asdfghjkl;'",
            "ASDFGHJKL:",
            "zxcvbnm,./",
            "ZXCVBNM<>?",
    };

    private static String SPECIAL_CHAR = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    /**
     * @author klw(213539@qq.com)
     * @Description: 检测密码长度是否符合要求
     * @Date 2019/11/6 11:13
     * @param: password
     * @param: minLength  最小长度
     * @param: maxLength  最大长度
     * @return boolean
     */
    public static boolean checkPasswordLength(String password, int minLength, int maxLength) {
        int pwdLength = password.length();
        if (minLength > pwdLength || maxLength < pwdLength) {
            return false;
        }
        return true;
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 检测密码是否是纯数字
     * @Date 2019/11/6 14:09
     * @param: spassword
     * @return boolean
     */
    public static boolean checkOnlyContainDigit(String spassword) {
        if (StringUtils.isNotBlank(spassword))
            return spassword.matches("^[0-9]*$");
        else
            return false;
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 检测密码中是否包含数字
     * @Date 2019/11/6 11:15
     * @param: password
     * @return boolean
     */
    public static boolean checkContainDigit(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int num_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isDigit(chPass[i])) {
                num_count++;
            }
        }
        if (num_count >= 1) {
            flag = true;
        }
        return flag;
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 检测密码中是否包含字母（不区分大小写）
     * @Date 2019/11/6 11:16
     * @param: password
     * @return boolean
     */
    public static boolean checkContainCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLetter(chPass[i])) {
                char_count++;
            }
        }
        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }


    /**
     * @author klw(213539@qq.com)
     * @Description: 检测密码中是否包含小写字母
     * @Date 2019/11/6 11:16
     * @param: password
     * @return boolean
     */
    public static boolean checkContainLowerCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isLowerCase(chPass[i])) {
                char_count++;
            }
        }
        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }


    /**
     * @author klw(213539@qq.com)
     * @Description: 检测密码中是否包含大写字母
     * @Date 2019/11/6 11:17
     * @param: password
     * @return boolean
     */
    public static boolean checkContainUpperCase(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int char_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (Character.isUpperCase(chPass[i])) {
                char_count++;
            }
        }
        if (char_count >= 1) {
            flag = true;
        }
        return flag;
    }


    /**
     * @author klw(213539@qq.com)
     * @Description: 检测密码中是否包含特殊符号
     * @Date 2019/11/6 11:17
     * @param: password
     * @return boolean
     */
    public static boolean checkContainSpecialChar(String password) {
        char[] chPass = password.toCharArray();
        boolean flag = false;
        int special_count = 0;

        for (int i = 0; i < chPass.length; i++) {
            if (SPECIAL_CHAR.indexOf(chPass[i]) != -1) {
                special_count++;
            }
        }

        if (special_count >= 1) {
            flag = true;
        }
        return flag;
    }


    /**
     * @author klw(213539@qq.com)
     * @Description: 是否包含键盘横向连续字符
     * @Date 2019/11/6 11:17
     * @param: password
     * @param: repetitions  连续个数
     * @param: isLower  是否区分大小写 true:区分大小写， false:不区分大小写
     * @return boolean
     */
    public static boolean checkLateralKeyboardSite(String password, int repetitions, boolean isLower) {
        String t_password = new String(password);
        //将所有输入字符转为小写
        t_password = t_password.toLowerCase();
        int n = t_password.length();
        /**
         * 键盘横向规则检测
         */
        boolean flag = false;
        int arrLen = KEYBOARD_HORIZONTAL_ARR.length;
        int limit_num = repetitions;

        for (int i = 0; i + limit_num <= n; i++) {
            String str = t_password.substring(i, i + limit_num);
            String distinguishStr = password.substring(i, i + limit_num);

            for (int j = 0; j < arrLen; j++) {
                String configStr = KEYBOARD_HORIZONTAL_ARR[j];
                String revOrderStr = new StringBuffer(KEYBOARD_HORIZONTAL_ARR[j]).reverse().toString();

                //检测包含字母(区分大小写)
                if (isLower) {
                    //考虑 大写键盘匹配的情况
                    String UpperStr = KEYBOARD_HORIZONTAL_ARR[j].toUpperCase();
                    if ((configStr.indexOf(distinguishStr) != -1) || (UpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                    if ((revOrderStr.indexOf(distinguishStr) != -1) || (revUpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                } else {
                    if (configStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    if (revOrderStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }


    /**
     * @author klw(213539@qq.com)
     * @Description: 是否包含键盘纵向连续字符
     * @Date 2019/11/6 11:20
     * @param: password
     * @param: repetitions  连续个数
     * @param: isLower  是否区分大小写 true:区分大小写， false:不区分大小写
     * @return boolean
     */
    public static boolean checkKeyboardSlantSite(String password, int repetitions, boolean isLower) {
        String t_password = new String(password);
        t_password = t_password.toLowerCase();
        int n = t_password.length();
        /**
         * 键盘斜线方向规则检测
         */
        boolean flag = false;
        int arrLen = KEYBOARD_SLOPE_ARR.length;
        int limit_num = repetitions;

        for (int i = 0; i + limit_num <= n; i++) {
            String str = t_password.substring(i, i + limit_num);
            String distinguishStr = password.substring(i, i + limit_num);
            for (int j = 0; j < arrLen; j++) {
                String configStr = KEYBOARD_SLOPE_ARR[j];
                String revOrderStr = new StringBuffer(KEYBOARD_SLOPE_ARR[j]).reverse().toString();
                //检测包含字母(区分大小写)
                if (isLower) {
                    //考虑 大写键盘匹配的情况
                    String UpperStr = KEYBOARD_SLOPE_ARR[j].toUpperCase();
                    if ((configStr.indexOf(distinguishStr) != -1) || (UpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    String revUpperStr = new StringBuffer(UpperStr).reverse().toString();
                    if ((revOrderStr.indexOf(distinguishStr) != -1) || (revUpperStr.indexOf(distinguishStr) != -1)) {
                        flag = true;
                        return flag;
                    }
                } else {
                    if (configStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                    //考虑逆序输入情况下 连续输入
                    if (revOrderStr.indexOf(str) != -1) {
                        flag = true;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 是否包含字母表连续字符
     * @Date 2019/11/6 11:21
     * @param: password
     * @param: repetitions  连续个数
     * @param: isLower  是否区分大小写 true:区分大小写， false:不区分大小写
     * @return boolean
     */
    public static boolean checkSequentialChars(String password, int repetitions, boolean isLower) {
        String t_password = new String(password);
        boolean flag = false;
        int limit_num = repetitions;
        int normal_count = 0;
        int reversed_count = 0;
        //检测包含字母(区分大小写)
        if (!isLower) {
            t_password = t_password.toLowerCase();
        }
        int n = t_password.length();
        char[] pwdCharArr = t_password.toCharArray();

        for (int i = 0; i + limit_num <= n; i++) {
            normal_count = 0;
            reversed_count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if (pwdCharArr[i + j + 1] - pwdCharArr[i + j] == 1) {
                    normal_count++;
                    if (normal_count == limit_num - 1) {
                        return true;
                    }
                }

                if (pwdCharArr[i + j] - pwdCharArr[i + j + 1] == 1) {
                    reversed_count++;
                    if (reversed_count == limit_num - 1) {
                        return true;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 是否包含连续相同字符, 如 aaa,qqq,!!!
     * @Date 2019/11/6 11:23
     * @param: password
     * @param: repetitions  连续次数
     * @return boolean
     */
    public static boolean checkSequentialSameChars(String password, int repetitions) {
        String t_password = new String(password);
        int n = t_password.length();
        char[] pwdCharArr = t_password.toCharArray();
        boolean flag = false;
        int limit_num = repetitions;
        int count = 0;
        for (int i = 0; i + limit_num <= n; i++) {
            count = 0;
            for (int j = 0; j < limit_num - 1; j++) {
                if (pwdCharArr[i + j] == pwdCharArr[i + j + 1]) {
                    count++;
                    if (count == limit_num - 1) {
                        return true;
                    }
                }
            }
        }
        return flag;
    }

}
