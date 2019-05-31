package top.klw8.alita.validator;

import java.io.IOException;
import java.util.Map;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;


/**
 * @author klw
 * @ClassName: EnableValidatorSelector
 * @Description: 将处理验证器的切面注入到spring中
 * @date 2018年9月17日 下午4:41:28
 */
public class EnableValidatorSelector implements ImportSelector, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableValidator.class.getName());
        Class<?> obj = (Class<?>) annotationAttributes.get("responseMsgGenerator");
        String[] otherImports = new String[]{ValidatorAOP.class.getName(), obj.getName()};

        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
        Resource[] resources;
        String[] validatorImports = null;
        try {
            resources = resolver.getResources("classpath*:top/klw8/alita/validator/annotations/impl/*.class");
            validatorImports = new String[resources.length];
            for (int i = 0; i < resources.length; i++) {
                MetadataReader reader = metaReader.getMetadataReader(resources[i]);
                validatorImports[i] = reader.getClassMetadata().getClassName();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(validatorImports != null && validatorImports.length > 0) {
            String[] allImports = new String[otherImports.length + validatorImports.length];
            System.arraycopy(otherImports, 0, allImports, 0, otherImports.length);
            System.arraycopy(validatorImports, 0, allImports, otherImports.length, validatorImports.length);
            return allImports;
        }
        return otherImports;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
