package cn.vtrump.testspringboot1.interceptor;

import cn.vtrump.testspringboot1.annotation.EncryptDecryptClass;
import cn.vtrump.testspringboot1.encryptdecrypt.EncryptDecrypt;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

@Component
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = Statement.class)
})
public class ResultInterceptor implements Interceptor {
    //定义一个加解密器，自定义加密规则为"wangruiting"
    EncryptDecrypt encryptDecrypt = new EncryptDecrypt("wangruiting");
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //result为执行query后返回的结果集，已转换为java type
        Object result = invocation.proceed();
        if(Objects.isNull(result)) {
            return null;
        }

        //返回的结果若是多个，返回形式为ArrayList
        if(result instanceof ArrayList){
            ArrayList resultList = (ArrayList) result;
            //若结果集是需要被解密的，就通过反射把结果集解密
            if(!CollectionUtils.isEmpty(resultList) && needToDecrypt(resultList.get(0))){
                for (Object o : resultList) {
                    Class<?> resultObjectClass = o.getClass();
                    Field nameField = resultObjectClass.getDeclaredField("name");//TODO:反射检测带EncryptDecryptField字段
                    nameField.setAccessible(true);
                    //把解密后的值直接赋给result，反射特性？
                    nameField.set(o, encryptDecrypt.decrypt((String) nameField.get(o)));
                }
            }
        }
        //返回的结果只有一个，直接解密
        else{
            if(needToDecrypt(result)){
                Class<?> resultObjectClass = result.getClass();
                Field nameField = resultObjectClass.getDeclaredField("name");//TODO:反射检测带EncryptDecryptField字段
                nameField.setAccessible(true);
                nameField.set(result, encryptDecrypt.decrypt((String) nameField.get(result)));
            }
        }
        return result;
    }

    //通过result实体是否有EncryptDecryptClass注解来判断该结果集是否需要被解密
    public boolean needToDecrypt(Object object){
        Class<?> objectClass = object.getClass();
        EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(objectClass, EncryptDecryptClass.class);
        return Objects.nonNull(encryptDecryptClass);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
