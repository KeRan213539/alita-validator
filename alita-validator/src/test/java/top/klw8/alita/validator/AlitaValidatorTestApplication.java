package top.klw8.alita.validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.klw8.alita.validator.beans.*;
import top.klw8.alita.validator.utils.ValidatorUtil;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

//        PrarmBean testBean = new PrarmBean();
//        testBean.setStr2("2222");
//        Calendar calendar=Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.DATE,-1);
//        testBean.setDate(calendar.getTime());
//        testBean.setLocalDate(LocalDate.now().plusDays(2));
//        testBean.setLocalDateTime(LocalDateTime.now().plusDays(1));
//        System.out.println(tv.testValidator3(testBean));

//        PrarmTrimBean prarmTrimBean = new PrarmTrimBean();
//        List<Object> list = new ArrayList<>();
//        list.add(1);
//        list.add(" asdf ");
//        list.add("             d                 ");
//        prarmTrimBean.setList(list);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("111", "rrr  ");
//        map.put("222", 123);
//        prarmTrimBean.setMap(map);
//
//        String[] array = new String[2];
//        array[0] = "   1   ";
//        array[1] = "    2";
//        prarmTrimBean.setArray(array);
//
//        prarmTrimBean.setStr("324sfsd     ");
//        System.out.println(tv.testValidatorTrim(prarmTrimBean));


        TestBean testBean = new TestBean();
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(" asdf ");
        list.add("             d                 ");
        testBean.setList(list);
        String url = "http://blog.csdn.net/wangchaoqi1985/article/details/82810471?abc=" + URLEncoder.encode("中文");
        testBean.setUrl(url);
        testBean.setDate("2019-08-08 18:18:18");
//        testBean.setIdNo("530102198808182568");
        testBean.setStrLength("123");
        TestBean3 testBean3 = new TestBean3();
        testBean3.setStrLength("123");
        TestBean2 testBean2 = new TestBean2();
        testBean2.setTestBean3(testBean3);
        testBean.setTestBean2(testBean2);
        System.out.println(tv.testValidator4(testBean));
        System.out.println("============DONE!!!================");
    }



}
