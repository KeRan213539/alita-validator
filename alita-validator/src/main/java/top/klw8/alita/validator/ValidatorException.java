package top.klw8.alita.validator;


/**
 * @author klw
 * @ClassName: ValidatorException
 * @Description: 表单验证, 业务异常
 * @date 2018年9月17日 13:07:33
 */
public class ValidatorException extends RuntimeException {

    private static final long serialVersionUID = 5968495544349839830L;

    private String statusCode;

    private String errorMsg;

    private Class<?> methodReturnType;

    public ValidatorException(String statusCode, String errorMsg) {
        super("【" + statusCode + "】" + errorMsg);
        this.statusCode = statusCode;
        this.errorMsg = errorMsg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setMethodReturnType(Class<?> methodReturnType){
        this.methodReturnType = methodReturnType;
    }

    public Class<?> getMethodReturnType(){
        return this.methodReturnType;
    }

}
