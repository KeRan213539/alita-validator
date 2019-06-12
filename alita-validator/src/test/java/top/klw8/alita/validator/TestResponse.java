package top.klw8.alita.validator;

/**
 * @author klw
 * @ClassName: TestResponse
 * @Description: 测试
 * @date 2019/6/12 9:42
 */
public class TestResponse implements IResponseMsgGenerator {
    @Override
    public Object generatorResponse(String code, String message, ValidatorException ex) {
        throw ex;
    }
}
