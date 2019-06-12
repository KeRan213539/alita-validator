package top.klw8.alita.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.klw8.alita.validator.annotations.IntegerRange;

/**
 * @author klw
 * @ClassName: TestApplication
 * @Description: 测试
 * @date 2019/6/12 9:41
 */
@SpringBootApplication
@EnableValidator(responseMsgGenerator = TestResponse.class)
public class TestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(TestApplication.class, args);
        TestValidator tv = applicationContext.getBean(TestValidator.class);
        System.out.println(tv.testValidator1(new PrarmBean(null, "111"), 1, 2));
    }

}
