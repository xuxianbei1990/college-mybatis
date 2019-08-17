package college.interceptor.mybatis.version_4;

import college.interceptor.mybatis.version_3.Interceptor;
import college.interceptor.mybatis.version_3.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Name 代理
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
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new TargetProxy(target, interceptor));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodName methodName = this.interceptor.getClass().getAnnotation(MethodName.class);
        if (methodName == null) {
            throw new NullPointerException("未指定方法");
        }
        if (method.getName().equals(methodName.value())) {
            return interceptor.intercept(new Invocation(target, method, args));
        }
        return method.invoke(target, args);
    }
}
