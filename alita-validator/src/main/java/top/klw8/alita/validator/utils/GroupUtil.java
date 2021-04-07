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
package top.klw8.alita.validator.utils;

import top.klw8.alita.validator.annotations.GroupItem;
import top.klw8.alita.validator.annotations.PasswordGroupItem;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: GroupUtil
 * @Description: 分组工具
 * @date 2019/11/5 15:39
 */
public class GroupUtil {

    /**
     * @author klw(213539@qq.com)
     * @Description: 获取指定 classz 中标注了@GroupItem 的全部字段
     * @Date 2019/11/6 9:55
     * @param: classz
     * @return java.util.List<java.lang.reflect.Field>
     */
    public static List<Field> getAllGroupItem(Class<?> classz){
        return getAllFieldByAnnotation(classz, GroupItem.class);
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 获取指定 classz 中标注了@PasswordGroupItem 的全部字段
     * @Date 2019/11/6 10:18
     * @param: classz
     * @return java.util.List<java.lang.reflect.Field>
     */
    public static List<Field> getAllPasswordGroupItem(Class<?> classz){
        return getAllFieldByAnnotation(classz, PasswordGroupItem.class);
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 获取指定 classz 中标注了指定注解的全部字段
     * @Date 2019/11/6 9:56
     * @param: classz
     * @param: annotation
     * @return java.util.List<java.lang.reflect.Field>
     */
    private static List<Field> getAllFieldByAnnotation(Class<?> classz, Class<? extends Annotation> annotation){
        List<Field> result = new ArrayList<>();
        if (classz == null) {
            return null;
        }
        List<Field> fieldList = Arrays.asList(classz.getDeclaredFields());
        for(Field f : fieldList){
            if(f.isAnnotationPresent(annotation)){
                result.add(f);
            }
        }
        return result;
    }

}
