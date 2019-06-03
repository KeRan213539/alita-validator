# Alita Validator

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

从Alita项目中抽取出来的注解验证器, 用于对方法参数做验证,如常用的非空,手机号格式,邮箱格式,数字范围等. 通常用于HTTP项目(包括Restful)中客户端提交的参数的验证

大家可能会问,spring MVC支持验证注解,如常用的hibernate-validator,为什么要自己实现一套呢?
最近做一个APP的服务端接口,项目中有自己的业务返回码.spring MVC支持的注解验证器无法设置验证不通过的时候的返回码,各种不方便,所以思前想后还是自己实现了一套

## 主要功能

* 基于Spring Boot 2.x

* **验证JAVA方法的参数是否符合要求**：验证器是基于注解的,可用于验证任何的JAVA方法的参数
* **自定义错误码**：使用注解的时候可以自定义验证失败时的错误码
* **自定义错误消息**: 使用注解的时候可以自定义验证失败的消息,该消息为一个字符串,如果做国际化,可以在错误消息中放入国际化资源标识,在responseMsgGenerator 处理该资源标识
* **返回类型自定**：通过实现错误响应接口,可以实现验证失败被调用的方法返回的内容自定,而不是抛出异常. 例如WEB项目中可以返回一个项目定义的Response格式的对象.
* **各种验证方式**：目前提供少量的验证器,如必传,非空,密码(复杂度,长度等),手机号格式等.后面会慢慢增加更多的验证器,当然大家也可以提PR

## 未来规划

* 目前验证只支持方法的参数是Java Bean,并且对象属性中需要定义验证器. 暂不支持参数为基础数据类型,Map,List等的验证,后面会支持

* 根据自用发现的需求增加功能



## 开始使用

**maven引入**:

```
<dependency>
    <groupId>top.klw8.alita</groupId>
    <artifactId>alita-validator</artifactId>
    <version>0.1</version>
</dependency>
```

**开发一个responseMsgGenerator**:

 responseMsgGenerator 作为验证错误的响应生成器,用于生成当验证不通过时被调用方法的返回的值,例如WBE项目用于验证客户端提交的参数,可以返回一个统一的响应结构.

实现top.klw8.alita.validator.IResponseMsgGenerator接口,根据传入的自定义错误码和自定义错误消息生成对应的返回值



**开启验证器**:

在配制类(标注了@Configuration的类)或者spring boot 启动器(标注了@SpringBootApplication的类)的类中加上类注解: 

```
@EnableValidator(responseMsgGenerator = ValidatorResponseGenerator.class)
```

其中 responseMsgGenerator 为上面开发的 responseMsgGenerator

**使用验证器**:

在需要验证的方法上加上方法注解 top.klw8.alita.validator.UseValidator:

```
@UseValidator
public CustomResult xxx(Demo demo)
```

在Demo类的属性上加上具体验证器:

```
@Required(validatFailMessage = "姓名不能为空", responseStatusCode="5008")
private String fullName;
```





## 开发验证器
验证器注解可以在使用的项目中单独开发,包名相同就行,当然也欢迎各种PR~

**定义验证器注解**

验证器注解放入 top.klw8.alita.validator.annotations 中

例如开发一个非空验证器(当值不为null的时候判断是否空,为null不处理):

```
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited
@ThisIsValidator
public @interface NotEmpty {

    @AliasFor("validatFailMessage")
    String value() default "";
    
    /**
     * @Title: responseStatusCode
     * @Description: 验证失败(不通过)的code
     * @return
     */
    String responseStatusCode() default "500";
    
    /**
     * @Title: validatFailMessage
     * @Description: 验证失败(不通过)的文字消息,可为空,默认使用ResponseStatusCodeEnum对应的消息
     * @return
     */
    @AliasFor("value")
    String validatFailMessage();
    
    
    // 更多参数...
}
```

上面例子中的value,responseStatusCode和 validatFailMessage 为每个验证器必须有的参数,当然还可以增加更多的参数,在验证器实现解析

 **开发验证器实现类**

验证器实现放入 top.klw8.alita.validator.annotations.impl 中

需要实现  top.klw8.alita.validator.IAnnotationsValidator 接口

```
@ValidatorImpl(validator = NotEmpty.class)  //标注该实现是哪个验证器的实现,如果是多个验证器共有的实现则可以validator = {NotEmpty.class, Xxx.class}
public class NotEmptyImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws Exception {
	NotEmpty notEmpty = (NotEmpty) annotation;
	String statusCode = notEmpty.responseStatusCode();
	String message = notEmpty.validatFailMessage();
	if (object != null) {
	    if (object.getClass().isArray()) {
		ValidatorUtil.notEmpty((Object[]) object, statusCode, message);
	    } else if (object instanceof Collection) {
		ValidatorUtil.notEmpty((Collection<?>) object, statusCode, message);
	    } else if (object instanceof Map) {
		ValidatorUtil.notEmpty((Map<?, ?>) object, statusCode, message);
	    } else if (object instanceof String) {
		ValidatorUtil.hasText((String) object, statusCode, message);
	    }
	}
    }

}
```

验证器实现不返回任值,验证不通过时抛出 top.klw8.alita.validator.ValidatorException 异常即可

