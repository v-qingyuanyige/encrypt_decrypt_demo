package cn.vtrump.testspringboot1.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptDecryptClass {
    //将此注解标注在待加解密的数据实体类上
}
