package top.klw8.alita.validator.annotations.impl;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: StringLengthImpl
 * @Description: StringLength 验证器实现
 * @date 2020/2/8 16:23
 */

import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.StringLength;

import java.lang.annotation.Annotation;

@ValidatorImpl(validator = StringLength.class)
public class StringLengthImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        if (object != null) {
            StringLength stringLength = (StringLength)annotation;
            String statusCode = stringLength.responseStatusCode();
            String message = stringLength.validatFailMessage();
            int minLength = stringLength.minLength();
            int maxLength = stringLength.maxLength();
            if(object instanceof String){
                int strLength = ((String)object).trim().length();
                if(strLength >= minLength && strLength <= maxLength){
                    return;
                }
            }
            throw new ValidatorException(statusCode, message);
        }
    }

}
