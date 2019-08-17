package college.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;

import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * Name mybatis 拦截器
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description 拦截语句
 */
@Intercepts(@Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class}))
public class ParameterHandlerInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
        Object o = parameterHandler.getParameterObject();
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
