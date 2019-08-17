package college.interceptor.mybatis.version_4;

import college.interceptor.mybatis.version_3.Interceptor;


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
        Target target = (Target) interceptor.register(new TargetImpl());
        target.executeNeedIntercepte();
        target.executeNot();
    }
}
