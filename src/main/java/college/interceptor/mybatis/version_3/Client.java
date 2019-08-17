package college.interceptor.mybatis.version_3;

import college.interceptor.mybatis.version_1.Target;
import college.interceptor.mybatis.version_1.TargetImpl;

/**
 * Name
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description
 */
public class Client {
    public static void main(String[] args) {
        Interceptor interceptor = new InterceptorImpl();
        // 但是不是所有的Target的方法都需要拦截的，所以出现了4.0版本
        Target target = (Target) interceptor.register(new TargetImpl());
        target.execute();
    }
}
