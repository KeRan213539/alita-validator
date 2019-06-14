package top.klw8.alita.validator.cfg;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author klw
 * @ClassName: CustomValidatorCfgBean
 * @Description: 自扩展验证器配制
 * @date 2019/6/13 10:21
 */
@ConfigurationProperties(prefix="alita.validator.custom")
@Getter
@Setter
public class CustomValidatorCfgBean {

    /**
     * @author klw
     * @Description: 自定义验证器实现所在的包
     */
    private List<String> packages;

}
