package college.interceptor.mybatis.version_3;

/**
 * Name
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description  挺不错一个思路，可以剥离依赖spring
 */
public class InterceptorImpl implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("拦截成功，这种实现方式就是mybatis 拦截器实现原理" +
                "它的思想就是把拦截逻辑彻底剥离。");
        return invocation.proceed();
    }

    @Override
    public Object register(Object target) {
        return TargetProxy.bind(target, this);
    }
}
