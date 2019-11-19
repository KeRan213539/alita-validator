package top.klw8.alita.validator;

import org.springframework.stereotype.Component;
import top.klw8.alita.validator.annotations.IntegerRange;
import top.klw8.alita.validator.annotations.Required;
import top.klw8.alita.validator.beans.GroupNotEmptyTestBean;
import top.klw8.alita.validator.beans.PrarmBean;

/**
 * @author klw
 * @ClassName: TestValidator
 * @Description: 测试验证器
 * @date 2019/6/12 9:49
 */
@Component
public class TestValidator {

    @UseValidator
    public String testValidator1(@Required("参数不能为空") PrarmBean prarmBean,
                                 @Required("页码不能为空") Integer pageNum,
                                 @IntegerRange(validatFailMessage = "每页显示至少2条", min = 2, max = 20) Integer pageSize) {
        return "OK";
    }

    @UseValidator
    public String testValidator2(@Required("bean 不能为空") GroupNotEmptyTestBean bean){
        return "OK";
    }

    @UseValidator
    public String testValidator3(@Required("参数不能为空") PrarmBean prarmBean) {
        return "OK";
    }

}
