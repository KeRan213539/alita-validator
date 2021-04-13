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
package top.klw8.alita.validator.annotations.enums;

/**
 * 密码强度枚举
 * 2019/11/6 13:32
 */
public enum PasswordStrengthLevel {

    /**
     * 纯数字
     */
    NUMBER,

    /**
     * 简单
     * 包含数字,包含字母
     * 每种至少包含一个
     */
    EASY,

    /**
     * 复杂
     * 包含数字,包含小写字母,包含大写字母,包含特殊符号
     * 每种至少包含一个
     */
    STRONG,

    /**
     * 非常复杂
     * 在 STRONG 的基础上增加:
     * 不包含键盘连续字符(横向,纵向),不包含字母表连续字符,不包含连续相同字符
     * 每种连续次数3次及以上则不符合要求
     */
    VERY_STRONG

}
