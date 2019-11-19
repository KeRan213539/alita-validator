package top.klw8.alita.validator;

import java.lang.annotation.Annotation;
import java.text.ParseException;

/**
 * @ClassName: IAnnotationsValidator
 * @Description: 注解验证器的验证接口,每种注解验证的实现需要实现该接口
 * @author klw
 * @date 2018年9月17日 13:07:53
 */
public interface IAnnotationsValidator {

    void doValidator(Object object, Annotation annotation) throws ValidatorException;
    
}
