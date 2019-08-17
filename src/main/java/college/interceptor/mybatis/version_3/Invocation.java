package college.interceptor.mybatis.version_3;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Name
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description https://blog.csdn.net/zxc123e/article/details/77119826
 * Interceptor接口中需要使用intercept方法传过去Method类，那么也需要了解它。那么既然Interceptor都需要使用Method，
 * 还不如将Method的执行也放到Interceptor中，不再让TargetProxy类对其了解。
 */
@Data
public class Invocation {
    private Object target;
    private Method method;
    private Object[] args;

    public Invocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    //进一步隔离代理
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }
}
