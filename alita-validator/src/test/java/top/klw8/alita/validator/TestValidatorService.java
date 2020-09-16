package top.klw8.alita.validator;

import org.springframework.stereotype.Component;
import top.klw8.alita.validator.annotations.IntegerRange;
import top.klw8.alita.validator.annotations.MultiFieldRequired;
import top.klw8.alita.validator.annotations.Required;
import top.klw8.alita.validator.beans.GroupNotEmptyTestBean;
import top.klw8.alita.validator.beans.PrarmBean;
import top.klw8.alita.validator.beans.PrarmTrimBean;
import top.klw8.alita.validator.beans.TestBean;

/**
 * @author klw
 * @ClassName: TestValidator
 * @Description: 测试验证器
 * @date 2019/6/12 9:49
 */
@Component
public class TestValidatorService {

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

    @UseValidator
    public String testValidatorTrim(@Required("参数不能为空") PrarmTrimBean prarmTrimBean) {
        return "OK";
    }

    @UseValidator
    public String testValidator4(@MultiFieldRequired({"list::555::list不能为空","url::666::url不能为空"}) TestBean bean) {
        return "OK";
    }

}
