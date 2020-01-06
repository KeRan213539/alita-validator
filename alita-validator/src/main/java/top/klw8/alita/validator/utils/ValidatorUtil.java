package top.klw8.alita.validator.utils;

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

}
