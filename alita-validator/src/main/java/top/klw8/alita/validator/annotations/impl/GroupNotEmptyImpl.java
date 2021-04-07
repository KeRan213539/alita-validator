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
package top.klw8.alita.validator.annotations.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.GroupItem;
import top.klw8.alita.validator.annotations.GroupNotEmpty;
import top.klw8.alita.validator.annotations.GroupNotEmptys;
import top.klw8.alita.validator.utils.GroupUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: GroupNotEmptyImpl
 * @Description: GroupNotEmpty 验证器实现
 * @date 2019/11/5 14:47
 */
@ValidatorImpl(validator = {GroupNotEmptys.class, GroupNotEmpty.class})
@Slf4j
public class GroupNotEmptyImpl implements IAnnotationsValidator {
    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        if(object != null){
            GroupNotEmpty[] groupNotEmptyArray;
            if(annotation instanceof GroupNotEmptys){
                GroupNotEmptys groupNotEmptys = (GroupNotEmptys)annotation;
                groupNotEmptyArray = groupNotEmptys.value();
            } else if(annotation instanceof GroupNotEmpty){
                groupNotEmptyArray = new GroupNotEmpty[1];
                groupNotEmptyArray[0] = (GroupNotEmpty)annotation;
            } else {
                groupNotEmptyArray = new GroupNotEmpty[0];
            }
            for(GroupNotEmpty groupNotEmpty : groupNotEmptyArray){
                String statusCode = groupNotEmpty.responseStatusCode();
                String message = groupNotEmpty.validatFailMessage();
                String groupName = groupNotEmpty.groupName();
                int notEmptyCountMin = groupNotEmpty.notEmptyCountMin();
                int okCount = 0;
                List<Field> groupItemList = GroupUtil.getAllGroupItem(object.getClass());

                if(CollectionUtils.isNotEmpty(groupItemList)){
                    for (Field field : groupItemList) {
                        GroupItem groupItem = field.getAnnotation(GroupItem.class);
                        String groupItemGroupName = groupItem.value();
                        if(groupName.equals(groupItemGroupName)) {
                            field.setAccessible(true);
                            try {
                                Object fieldVale = field.get(object);
                                if (checkIsNotEmpty(fieldVale)) {
                                    ++okCount;
                                }
                            } catch (IllegalAccessException e) {
                                log.error("反射获取字段值出错", e);
                                throw new ValidatorException(statusCode, "反射获取字段值出错");
                            }
                        }
                    }
                }
                if(okCount < notEmptyCountMin){
                    throw new ValidatorException(statusCode, message);
                }
            }
        }
    }

    /**
     * @author klw(213539@qq.com)
     * @Description: 检查是否不为空,不为空则返回 true, 为空返回false
     */
    private boolean checkIsNotEmpty(Object object){
        if(object != null) {
            if (object.getClass().isArray()) {
                return !ObjectUtils.isEmpty((Object[]) object);
            } else if (object instanceof Collection) {
                return CollectionUtils.isNotEmpty((Collection<?>) object);
            } else if (object instanceof Map) {
                return MapUtils.isNotEmpty((Map<?, ?>) object);
            } else if (object instanceof String) {
                return StringUtils.hasText((String) object);
            }
        }
        return false;
    }
}
