/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.klw8.alita.validator.utils;

import java.util.*;

/**
 * @author klw(213539 @ qq.com)
 * @ClassName: TrimStringUtil
 * @Description:  自动去除字符串的前后空格,如果被注解的是集合(目前支持 Collection, Map, 字符串数组)
 * @date 2019/12/10 9:51
 */
public class TrimStringUtil {

    public static Object trim(Object object){
        if (object != null) {
            if (object.getClass().isArray()) {
                Object[] objectArray = (Object[]) object;
                for(int i = 0; i < objectArray.length; i++){
                    Object arrayItem = objectArray[i];
                    if (arrayItem instanceof String){
                        objectArray[i] = ((String) arrayItem).trim();
                    } else{
                        return object;
                    }
                }
            } else if (object instanceof Collection) {
                Collection objectCollection = (Collection) object;
                Iterator objectCollectionIt = objectCollection.iterator();
                List<Object> newObjectCollection = new ArrayList<>(objectCollection.size());
                while (objectCollectionIt.hasNext()){
                    Object collectionItem = objectCollectionIt.next();
                    if(collectionItem instanceof String){
                        newObjectCollection.add(((String) collectionItem).trim());
                    } else {
                        newObjectCollection.add(collectionItem);
                    }
                }
                object = newObjectCollection;
            } else if (object instanceof Map) {
                Map objMap = (Map)object;
                Set<Map.Entry> objMapEntrySet = objMap.entrySet();
                for(Map.Entry objMapEntry : objMapEntrySet){
                    Object objMapEntryValue = objMapEntry.getValue();
                    if(objMapEntryValue instanceof String){
                        objMap.put(objMapEntry.getKey(), ((String) objMapEntryValue).trim());
                    }
                }
            } else if (object instanceof String) {
                object = ((String) object).trim();
            }
        }
        return object;
    }

}
