package top.klw8.alita.validator.utils;

import top.klw8.alita.validator.annotations.GroupItem;

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

    public static List<Field> getAllGroupItem(Class<?> classz){
        List<Field> result = new ArrayList<>();
        if (classz == null) {
            return null;
        }
        List<Field> fieldList = Arrays.asList(classz.getDeclaredFields());
        for(Field f : fieldList){
            if(f.isAnnotationPresent(GroupItem.class)){
                result.add(f);
            }
        }
        return result;
    }

}
