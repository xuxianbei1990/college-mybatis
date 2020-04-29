package college.interceptor.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Properties;

/**
 * 金额转换
 * @author: xuxianbei
 * Date: 2020/4/29
 * Time: 9:50
 * Version:V1.0
 *
 *    因为mybatis plus 的 mapper 是用 MybatisDefaultParameterHandler 而 ServiceImpl 是自己手动创建的sqlSession 无法拦截 sqlSession
 *    改用拦截Executor；只测试
 */
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Component
public class MybatisMoneyExchageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof MybatisDefaultParameterHandler) {
            handleMybatisPlus(invocation);
        } else if (invocation.getTarget() instanceof Executor) {
            handleMybatis(invocation);
        }
        return invocation.proceed();
    }

    private void handleMybatis(Invocation invocation) {
        for (Object arg : invocation.getArgs()) {
            handleTarget(arg);
        }
    }

    private void handleMybatisPlus(Invocation invocation) {
        MybatisDefaultParameterHandler defaultParameterHandler = (MybatisDefaultParameterHandler) invocation.getTarget();
        if (defaultParameterHandler.getParameterObject() instanceof HashMap) {
            HashMap<String, Object> hashMap = (HashMap) defaultParameterHandler.getParameterObject();
            hashMap.entrySet().forEach(entry -> {
                Object object = entry.getValue();
                handleTarget(object);
            });
        }
    }

    private void handleTarget(Object object) {
        Class<?> clazz = object.getClass();
        for (Field declaredField : clazz.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(MoneyExchange.class)) {
                MoneyExchange moneyExchange = declaredField.getAnnotation(MoneyExchange.class);
                declaredField.setAccessible(true);
                try {
                    if (declaredField.get(object) instanceof Integer) {
                        Integer objectValue = (Integer) declaredField.get(object);
                        declaredField.set(object, objectValue * moneyExchange.value());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public Object plugin(Object target) {
        /**
         * 因为mybatis plus 的 mapper 是用 MybatisDefaultParameterHandler 而 ServiceImpl 是自己手动创建的sqlSession 无法拦截 sqlSession
         * 改用拦截Executor；只测试
         */
        if (target instanceof MybatisDefaultParameterHandler || target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
