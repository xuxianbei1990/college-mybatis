package college.interceptor.mybatis.version_1;

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
    public void execute() {
        System.out.println("我是需要被拦截对象。");
    }
}
