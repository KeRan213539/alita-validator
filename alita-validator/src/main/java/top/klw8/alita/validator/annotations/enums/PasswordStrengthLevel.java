package top.klw8.alita.validator.annotations.enums;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: PasswordStrengthLevel
 * @Description: 密码强度枚举
 * @date 2019/11/6 13:32
 */
public enum PasswordStrengthLevel {

    /**
     * @author klw(213539@qq.com)
     * @Description: 纯数字
     */
    NUMBER,

    /**
     * @author klw(213539@qq.com)
     * @Description: 简单
     * 包含数字,包含字母
     * 每种至少包含一个
     */
    EASY,

    /**
     * @author klw(213539@qq.com)
     * @Description: 复杂
     * 包含数字,包含小写字母,包含大写字母,包含特殊符号
     * 每种至少包含一个
     */
    STRONG,

    /**
     * @author klw(213539@qq.com)
     * @Description: 非常复杂
     * 在 STRONG 的基础上增加:
     * 不包含键盘连续字符(横向,纵向),不包含字母表连续字符,不包含连续相同字符
     * 每种连续次数3次及以上则不符合要求
     */
    VERY_STRONG

}
