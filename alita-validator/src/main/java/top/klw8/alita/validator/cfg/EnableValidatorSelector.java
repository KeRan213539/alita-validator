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
package top.klw8.alita.validator.cfg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import top.klw8.alita.validator.EnableValidator;
import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorAOP;
import top.klw8.alita.validator.ValidatorImpl;


/**
 * 将处理验证器的切面注入到spring中
 * 2018年9月17日 下午4:41:28
 */
public class EnableValidatorSelector implements ImportSelector, ResourceLoaderAware, EnvironmentAware {

    private ResourceLoader resourceLoader;

    private Environment environment;

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableValidator.class.getName());
        Class<?> obj = (Class<?>) annotationAttributes.get("responseMsgGenerator");
        String[] otherImports = new String[]{ValidatorAOP.class.getName(), obj.getName()};

        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
        String[] validators = this.scanPackage("top.klw8.alita.validator.annotations.impl", resolver, metaReader);

        String packages = environment.getProperty("alita.validator.custom.packages", String.class);
        List<String[]> tempList = null;
        int customCount = 0;
        if(StringUtils.isNotBlank(packages)){
            String[] packagesArr = packages.split(",");
            tempList = new ArrayList<>(packagesArr.length);
            for(String packageStr : packagesArr){
                String[] tempValidators = this.scanPackage(packageStr, resolver, metaReader);
                if(tempValidators != null && tempValidators.length > 0) {
                    customCount = customCount + tempValidators.length;
                    tempList.add(tempValidators);
                }
            }
        }
        String[] validatorImports = new String[validators.length + customCount];
        int destPos = 0;
        System.arraycopy(validators, 0, validatorImports, destPos, validators.length);
        destPos = validators.length;
        if(tempList != null){
            for(String[] temp : tempList){
                System.arraycopy(temp, 0, validatorImports, destPos, temp.length);
                destPos = destPos + temp.length;
            }
        }

        if(validatorImports != null && validatorImports.length > 0) {
            // 找出 validatorImports 中有 @ValidatorImpl 注解的类,只导入这些类
            List<String> realValidatorList = new ArrayList<>(validatorImports.length);
            for(String className : validatorImports){
                if(StringUtils.isBlank(className)){
                    continue;
                }
                try {
                    Class<?> classz = Class.forName(className);
                    if(classz.isAnnotationPresent(ValidatorImpl.class)){
                        if(IAnnotationsValidator.class.isAssignableFrom(classz)) {
                            realValidatorList.add(className);
                        } else {
                            System.out.println("【警告】" + classz.getName() + "使用 @ValidatorImpl 标记为了一个验" +
                                    "证器实现,但是没有实现接口 " + IAnnotationsValidator.class.getName()
                                    + ",将被忽略. 如果使用了该实现对应的注解,会出现异常");
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            validatorImports = new String[realValidatorList.size()];
            realValidatorList.toArray(validatorImports);

            String[] allImports = new String[otherImports.length + validatorImports.length];
            System.arraycopy(otherImports, 0, allImports, 0, otherImports.length);
            System.arraycopy(validatorImports, 0, allImports, otherImports.length, validatorImports.length);
            return allImports;
        }
        return otherImports;
    }

    private String[] scanPackage(String packageStr, ResourcePatternResolver resolver, MetadataReaderFactory metaReader){
        Resource[] resources;
        String[] result;
        try {
            packageStr = packageStr.replace(".", "/");
            resources = resolver.getResources("classpath*:" + packageStr + "/*.class");
            result = new String[resources.length];
            for (int i = 0; i < resources.length; i++) {
                MetadataReader reader = metaReader.getMetadataReader(resources[i]);
                if(reader.getClassMetadata().isConcrete()) {
                    result[i] = reader.getClassMetadata().getClassName();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
