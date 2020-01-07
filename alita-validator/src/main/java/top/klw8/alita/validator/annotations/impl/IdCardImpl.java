package top.klw8.alita.validator.annotations.impl;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: IdCardImpl
 * @Description: 身份证号验证器实现
 * @date 2020/1/7 11:30
 */

import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.IdCard;
import top.klw8.alita.validator.utils.ValidatorUtil;

import java.lang.annotation.Annotation;

@ValidatorImpl(validator = IdCard.class)
public class IdCardImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        if (object != null) {
            IdCard idCard = (IdCard)annotation;
            String statusCode = idCard.responseStatusCode();
            String message = idCard.validatFailMessage();
            if(object instanceof String && ValidatorUtil.isIdCardNumber((String)object)){
                return;
            }
            throw new ValidatorException(statusCode, message);
        }
    }

}
