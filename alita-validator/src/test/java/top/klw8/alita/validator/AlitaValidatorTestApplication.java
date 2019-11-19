package top.klw8.alita.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.klw8.alita.validator.beans.GroupNotEmptyTestBean;
import top.klw8.alita.validator.beans.PrarmBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        PrarmBean testBean = new PrarmBean();
        testBean.setStr2("2222");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-1);
        testBean.setDate(calendar.getTime());
        testBean.setLocalDate(LocalDate.now().plusDays(2));
        testBean.setLocalDateTime(LocalDateTime.now().plusDays(1));

        System.out.println(tv.testValidator3(testBean));
    }



}
