package college.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Properties;

/**更新时间默认取本项目时间
 * @author: xuxianbei
 * Date: 2020/9/3
 * Time: 14:08
 * Version:V1.0
 */
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
@Component
public class ExecutorrInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object object = invocation.getArgs()[1];
        Class<?> clazz = object.getClass();
        for (Field declaredField : clazz.getDeclaredFields()) {
            if (declaredField.getName().equals("updateDate")) {
                try {
                    declaredField.setAccessible(true);
                    declaredField.set(object, LocalDateTime.now());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
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
