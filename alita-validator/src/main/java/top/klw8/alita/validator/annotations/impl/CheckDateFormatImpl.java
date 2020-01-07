package top.klw8.alita.validator.annotations.impl;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: CheckDateFormatImpl
 * @Description: 日期格式验证器实现
 * @date 2020/1/7 11:29
 */

import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.CheckDateFormat;
import top.klw8.alita.validator.utils.ValidatorUtil;

import java.lang.annotation.Annotation;

@ValidatorImpl(validator = CheckDateFormat.class)
public class CheckDateFormatImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        if (object != null) {
            CheckDateFormat checkDateFormat = (CheckDateFormat)annotation;
            String statusCode = checkDateFormat.responseStatusCode();
            String message = checkDateFormat.validatFailMessage();
            String format = checkDateFormat.format();
            if(object instanceof String){
                ValidatorUtil.checkDateFormat((String)object, format, statusCode, message);
            } else {
                throw new ValidatorException(statusCode, message);
            }
        }
    }

}
