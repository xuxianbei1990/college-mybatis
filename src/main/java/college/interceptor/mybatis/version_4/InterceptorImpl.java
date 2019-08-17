package college.interceptor.mybatis.version_4;

import college.interceptor.mybatis.version_3.Interceptor;
import college.interceptor.mybatis.version_3.Invocation;

/**
 * Name
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description
 */
@MethodName("executeNeedIntercepte")
public class InterceptorImpl implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("拦截成功:" + invocation.getMethod().getName());
        return invocation.proceed();
    }

    @Override
    public Object register(Object target) {
        //如果是mybatis 这个对象应该就是mapper 下的xml；应该可以拿到完整的xml
        return TargetProxy.bind(target, this);
    }
}
