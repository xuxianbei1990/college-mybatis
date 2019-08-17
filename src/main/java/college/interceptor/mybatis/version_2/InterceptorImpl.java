package college.interceptor.mybatis.version_2;

import java.lang.reflect.Method;

/**
 * Name
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description
 */
public class InterceptorImpl implements Interceptor {
    @Override
    public void interceptor() {
        // 如果我需要拦截具体某个方法呢？
        System.out.println("我是脱离业务逻辑的拦截实现类");
    }

    @Override
    public void interceptor(Method method, Object[] args) {
        System.out.println("我是脱离业务逻辑的拦截实现类 + 拦截参数");
    }


}
