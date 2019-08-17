package college.interceptor.mybatis.version_4;


/**
 * Name
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description
 */
public class TargetImpl implements Target {

    @Override
    public void executeNeedIntercepte() {
        System.out.println("我是需要被拦截对象。executeNeedIntercepte");
    }

    @Override
    public void executeNot() {
        System.out.println("我是不需要被拦截对象。executeNot");
    }
}
