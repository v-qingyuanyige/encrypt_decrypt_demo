package cn.vtrump.testspringboot1.interceptor;

import cn.vtrump.testspringboot1.annotation.EncryptDecryptClass;
import cn.vtrump.testspringboot1.annotation.EncryptDecryptField;
import cn.vtrump.testspringboot1.encryptdecrypt.EncryptDecrypt;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Properties;

@Component
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class)
})
public class ParameterInterceptor implements Interceptor {

    //定义一个加解密器，自定义加密规则为"wangruiting"
    EncryptDecrypt encryptDecrypt = new EncryptDecrypt("wangruiting");
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(invocation.getTarget() instanceof ParameterHandler){
            ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
            //获取SQL语句（Insert、Update）执行时所带参数对象parameterField（pojo，是sqlSession的方法被调用时传入的数据实体）
            Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
            parameterField.setAccessible(true);
            Object parameterObject = parameterField.get(parameterHandler);

            if(Objects.nonNull(parameterObject)){
                Class<?> parameterObjectClass = parameterObject.getClass();
                //检查parameterObject是否带有EncryptDecryptClass注解，没有会返回Null
                EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(parameterObjectClass, EncryptDecryptClass.class);
                if(Objects.nonNull(encryptDecryptClass)){
                    Field[] parameterObjectFields = parameterObjectClass.getDeclaredFields();
                    for(Field f : parameterObjectFields){
                        if(f.isAnnotationPresent(EncryptDecryptField.class)){
                            f.setAccessible(true);
                            //TODO:Encrypt and set
                            f.set(parameterObject , encryptDecrypt.encrypt((String) f.get(parameterObject)));
                            f.setAccessible(false);
                        }
                    }
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
