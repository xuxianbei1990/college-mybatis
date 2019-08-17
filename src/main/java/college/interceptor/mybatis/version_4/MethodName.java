package college.interceptor.mybatis.version_4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Name
 *
 * @author xuxb
 * Date 2018-12-22
 * VersionV1.0
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodName {
    String value() default "";
}
