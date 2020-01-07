package top.klw8.alita.validator.annotations.impl;

import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.HttpUrl;
import top.klw8.alita.validator.utils.ValidatorUtil;

import java.lang.annotation.Annotation;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: HttpUrlImpl
 * @Description: HttpUrl验证器实现
 * @date 2020/1/6 17:10
 */
@ValidatorImpl(validator = HttpUrl.class)
public class HttpUrlImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        if (object != null) {
            HttpUrl hthpUrl = (HttpUrl)annotation;
            String statusCode = hthpUrl.responseStatusCode();
            String message = hthpUrl.validatFailMessage();
            if(object instanceof String){
                ValidatorUtil.isHttpUrl((String)object, statusCode, message);
            } else {
                throw new ValidatorException(statusCode, message);
            }
        }
    }

}
