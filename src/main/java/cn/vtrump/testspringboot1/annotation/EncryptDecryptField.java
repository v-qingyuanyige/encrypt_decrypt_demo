package cn.vtrump.testspringboot1.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptDecryptField {
    //将此注解标注在待加解密的数据实体类的待加解密字段上
}
