package college.interceptor.mybatis.version_2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Name
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description
 */
public class TargetProxy implements InvocationHandler {
    private Object target;
    private Interceptor interceptor;

    public TargetProxy(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    public static Object bind(Object target, Interceptor interceptor) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new TargetProxy(target, interceptor));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这样就剥离了拦截的业务逻辑了
        if (interceptor != null) {
            interceptor.interceptor();
        }
        // 更加细致，我可以拦击具体参数，方法了
        // 可以更加优化 迪米特法则
        if (interceptor != null) {
            interceptor.interceptor(method, args);
        }
        return method.invoke(target, args);
    }
}
