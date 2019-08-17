package college.interceptor.mybatis.version_2;

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
        Target target = new TargetImpl();
        target.execute();
        System.out.println("对比");
        target = (Target) TargetProxy.bind(target, new InterceptorImpl());
        target.execute();
    }
}
