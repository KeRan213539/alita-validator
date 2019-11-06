package top.klw8.alita.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.klw8.alita.validator.beans.GroupNotEmptyTestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author klw
 * @ClassName: AlitaValidatorTestApplication
 * @Description: alita验证器测试
 * @date 2019/6/12 9:41
 */
@SpringBootApplication
@EnableValidator(responseMsgGenerator = TestResponse.class)
public class AlitaValidatorTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AlitaValidatorTestApplication.class, args);
        TestValidator tv = applicationContext.getBean(TestValidator.class);

//        System.out.println(tv.testValidator1(new PrarmBean(null, "111"), 1, 2));


        GroupNotEmptyTestBean testBean = new GroupNotEmptyTestBean();
        testBean.setStr1("123");
        List<String> list = new ArrayList<>(1);
        list.add("abc");
        testBean.setList1(list);
        testBean.setStr2("456");
        testBean.setPwd("$134qsE");
        testBean.setPwd1("$134qwe");
        testBean.setPwd2("$134qwe");
        System.out.println(tv.testValidator2(testBean));
    }



}
