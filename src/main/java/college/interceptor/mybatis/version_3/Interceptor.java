package college.interceptor.mybatis.version_3;

/**
 * Name
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description
 */
public interface Interceptor {

    Object intercept(Invocation invocation) throws Throwable;

    Object register(Object target);
}
