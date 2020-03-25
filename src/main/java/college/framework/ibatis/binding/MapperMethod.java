package college.framework.ibatis.binding;

import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.reflection.ParamNameResolver;
import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.SqlSession;
import lombok.Data;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 18:03
 * Version:V1.0
 */
public class MapperMethod {

    private final SqlCommand command;
    private final MethodSignature method;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration config) {
        this.command = new SqlCommand(config, mapperInterface, method);
        this.method = new MethodSignature(config, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result;
        switch (command.getType()) {
            case SELECT:
                if (method.returnsVoid) {
                    result = null;
                } else if (method.returnsMany) {
                    result = executeForMany(sqlSession, args);
                } else {
                    Object param = method.convertArgsToSqlCommandParam(args);
                    result = sqlSession.selectOne(command.getName(), param);
                }
                break;
        }
        return null;
    }

    private <E> Object executeForMany(SqlSession sqlSession, Object[] args) {
        List<E> result = null;
        Object param = method.convertArgsToSqlCommandParam(args);
        if (method.hasRowBounds()) {

        }  else {
            result = sqlSession.<E>selectList(command.getName(), param);
        }
        return result;
    }

    private Integer getUniqueParamIndex(Method method, Class<?> paramType) {
        Integer index = null;
        final Class<?>[] argTypes = method.getParameterTypes();
        for (int i = 0; i < argTypes.length; i++) {
            if (paramType.isAssignableFrom(argTypes[i])) {
                if (index == null) {
                    index = i;
                } else {
                    throw new BindingException(method.getName() + " cannot have multiple " + paramType.getSimpleName() + " parameters");
                }
            }
        }
        return index;
    }



    public static class SqlCommand {

        private final SqlCommandType type;
        private final String name;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            type = SqlCommandType.SELECT;
            final String methodName = method.getName();
            final Class<?> declaringClass = method.getDeclaringClass();
            MappedStatement ms = resolveMappedStatement(mapperInterface, methodName, declaringClass,
                    configuration);
            name = ms.getId();
        }

        public SqlCommandType getType() {
            return type;
        }

        private MappedStatement resolveMappedStatement(Class<?> mapperInterface, String methodName,
                                                       Class<?> declaringClass, Configuration configuration) {
            String statementId = mapperInterface.getName() + "." + methodName;
            if (configuration.hasStatement(statementId))
                return configuration.getMappedStatement(statementId);
            return null;
        }

        public String getName() {
            return name;
        }
    }


    public static class ParamMap<V> extends HashMap<String, V> {

        private static final long serialVersionUID = -2212268410512043556L;

        @Override
        public V get(Object key) {
            if (!super.containsKey(key)) {
                throw new BindingException("Parameter '" + key + "' not found. Available parameters are " + keySet());
            }
            return super.get(key);
        }

    }

    @Data
    public static class MethodSignature {

        private final Class<?> returnType;
        private final boolean returnsVoid;
        private final String mapKey;
        private final boolean returnsMany;
        private final ParamNameResolver paramNameResolver;
        private final Integer rowBoundsIndex;
//        private final Integer resultHandlerIndex;

        public MethodSignature(Configuration config, Class<?> mapperInterface, Method method) {
            this.returnType = method.getReturnType();
            this.returnsVoid = void.class.equals(this.returnType);
            this.mapKey = getMapKey(method);
            this.returnsMany = Collection.class.isAssignableFrom(this.returnType) || this.returnType.isArray();
            this.paramNameResolver = new ParamNameResolver(config, method);
            this.rowBoundsIndex = getUniqueParamIndex(method, RowBounds.class);
//            this.resultHandlerIndex = getUniqueParamIndex(method, ResultHandler.class);
        }

        public String getMapKey() {
            return mapKey;
        }
        public boolean hasRowBounds() {
            return rowBoundsIndex != null;
        }

        private Integer getUniqueParamIndex(Method method, Class<?> paramType) {
            Integer index = null;
            final Class<?>[] argTypes = method.getParameterTypes();
            for (int i = 0; i < argTypes.length; i++) {
                if (paramType.isAssignableFrom(argTypes[i])) {
                    if (index == null) {
                        index = i;
                    } else {
                        throw new BindingException(method.getName() + " cannot have multiple " + paramType.getSimpleName() + " parameters");
                    }
                }
            }
            return index;
        }

        private String getMapKey(Method method) {
            String mapKey = null;
            if (Map.class.isAssignableFrom(method.getReturnType())) {
                final MapKey mapKeyAnnotation = method.getAnnotation(MapKey.class);
                if (mapKeyAnnotation != null) {
                    mapKey = mapKeyAnnotation.value();
                }
            }
            return mapKey;
        }
        public Class<?> getReturnType() {
            return returnType;
        }

        public Object convertArgsToSqlCommandParam(Object[] args) {
            return paramNameResolver.getNamedParams(args);
        }
    }

}
