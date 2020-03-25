package college.framework.ibatis.reflection;

import college.framework.ibatis.binding.MapperMethod;
import college.framework.ibatis.session.Configuration;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.reflection.Jdk;
import org.apache.ibatis.reflection.ParamNameUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 20:37
 * Version:V1.0
 */
public class ParamNameResolver {
    private static final String GENERIC_NAME_PREFIX = "param";

    private boolean hasParamAnnotation;

    private final SortedMap<Integer, String> names;

    public ParamNameResolver(Configuration config, Method method) {
        final Class<?>[] paramTypes = method.getParameterTypes();
        final Annotation[][] paramAnnotations = method.getParameterAnnotations();
        final SortedMap<Integer, String> map = new TreeMap<Integer, String>();
        int paramCount = paramAnnotations.length;
        for (int paramIndex = 0; paramIndex < paramCount; paramIndex++) {
            String name = null;
            for (Annotation annotation : paramAnnotations[paramIndex]) {
                if (annotation instanceof Param) {
                    hasParamAnnotation = true;
                    name = ((Param) annotation).value();
                    break;
                }
            }
            if (name == null) {
                name = getActualParamName(method, paramIndex);
            }
            map.put(paramIndex, name);
        }
        //也就是说这里的排序和系统的传递参数的排序是一样的
        names = Collections.unmodifiableSortedMap(map);
    }

    //jdk 提供方法从方法中拿参数名称
    private String getActualParamName(Method method, int paramIndex) {
        if (Jdk.parameterExists) {
            return ParamNameUtil.getParamNames(method).get(paramIndex);
        }
        return null;
    }

    //假设参数是 3， key， 34a
    public Object getNamedParams(Object[] args) {
        final int paramCount = names.size();
        if (args == null || paramCount == 0) {
            return null;
        } else if (!hasParamAnnotation && paramCount == 1) {
            return args[names.firstKey()];
        } else {
            final Map<String, Object> param = new MapperMethod.ParamMap<Object>();
            int i = 0;
            for (Map.Entry<Integer, String> entry : names.entrySet()) {
                param.put(entry.getValue(), args[entry.getKey()]);
                // add generic param names (param1, param2, ...)
                final String genericParamName = GENERIC_NAME_PREFIX + String.valueOf(i + 1);
                // ensure not to overwrite parameter named with @Param
                if (!names.containsValue(genericParamName)) {
                    param.put(genericParamName, args[entry.getKey()]);
                }
                i++;
            }
            return param;
        }
    }
}