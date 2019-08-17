package college.interceptor.mybatis.version_1;

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

    private TargetProxy(Object target) {
        this.target = target;
    }

    // 得到一个拦截器
    public static Object bing(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new TargetProxy(target));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 缺点拦截的业务逻辑被写在拦截的流程当中，如果我要扩展拦截逻辑，还要改这里代码
        System.out.println("拦截目标对象成功");
        return method.invoke(target, args);
    }
}
