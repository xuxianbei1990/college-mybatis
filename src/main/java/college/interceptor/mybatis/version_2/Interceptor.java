package college.interceptor.mybatis.version_2;

import java.lang.reflect.Method;

/**
 * Name 拦截器
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description 把拦截的业务逻辑提取出来。
 */
public interface Interceptor {
    void interceptor();

    void interceptor(Method method, Object[] args);
}
