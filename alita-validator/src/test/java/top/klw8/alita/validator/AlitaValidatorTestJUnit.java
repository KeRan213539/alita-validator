/*
 * Copyright 2018-2021, ranke (213539@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.klw8.alita.validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.klw8.alita.validator.beans.PrarmBean;
import top.klw8.alita.validator.beans.PrarmTrimBean;
import top.klw8.alita.validator.beans.TestBean;
import top.klw8.alita.validator.beans.TestBean2;
import top.klw8.alita.validator.beans.TestBean3;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JUnit测试类.
 *
 * @author klw(213539 @ qq.com)
 * @ClassName: AlitaValidatorTestJUnit
 * @date 2020/9/16 10:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableValidator(responseMsgGenerator = TestResponse.class)
public class AlitaValidatorTestJUnit {
    
    @Autowired
    private TestValidatorService service;
    
    @Test
    public void test1(){
        PrarmBean testBean = new PrarmBean();
        testBean.setStr2("2222");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,-1);
        testBean.setDate(calendar.getTime());
        testBean.setLocalDate(LocalDate.now().plusDays(2));
        testBean.setLocalDateTime(LocalDateTime.now().plusDays(1));
        Assert.assertEquals("OK", service.testValidator3(testBean));
    }
    
    @Test
    public void test2(){
        PrarmTrimBean prarmTrimBean = new PrarmTrimBean();
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(" asdf ");
        list.add("             d                 ");
        prarmTrimBean.setList(list);
    
        Map<String, Object> map = new HashMap<>();
        map.put("111", "rrr  ");
        map.put("222", 123);
        prarmTrimBean.setMap(map);
    
        String[] array = new String[2];
        array[0] = "   1   ";
        array[1] = "    2";
        prarmTrimBean.setArray(array);
    
        prarmTrimBean.setStr("324sfsd     ");
        Assert.assertEquals("OK", service.testValidatorTrim(prarmTrimBean));
    }
    
    @Test
    public void test3(){
        TestBean2 testBean2 = new TestBean2();
        testBean2.setTestBean3(new TestBean3("123"));
        TestBean3[] testBean3Array = new TestBean3[1];
        testBean3Array[0] = new TestBean3("arr");
        List<TestBean3> testBean3List = new ArrayList<>(1);
        testBean3List.add(new TestBean3("li1"));
        testBean3List.add(new TestBean3("li2"));
        testBean3List.add(new TestBean3("li3"));
        Map<String, TestBean3> testBean3Map = new HashMap<>(1);
        testBean3Map.put("test", new TestBean3("map"));
    
        testBean2.setTestBean3Array(testBean3Array);
        testBean2.setTestBean3List(testBean3List);
        testBean2.setTestBean3Map(testBean3Map);
    
        TestBean testBean = new TestBean();
        testBean.setTestBean2(testBean2);
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
        Assert.assertEquals("OK", service.testValidator4(testBean));
    }

}
