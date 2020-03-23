package college.framework.ibatis.binding;

import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.SqlSession;
import org.apache.ibatis.binding.BindingException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:55
 * Version:V1.0
 * 代理模式，工厂模式
 */
public class MapperRegistry {

    private final Configuration config;
    //这样设计的好处就是方便扩展
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<Class<?>, MapperProxyFactory<?>>();

    public MapperRegistry(Configuration config) {
        this.config = config;
    }


    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        return mapperProxyFactory.newInstance(sqlSession);
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> void addMapper(Class<T> type) {
        if (!type.isInterface()) {
            return;
        }
        if (hasMapper(type)) {
            throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
        }
        boolean loadCompleted = false;
        try {
            knownMappers.putIfAbsent(type, new MapperProxyFactory<>(type));
            loadCompleted = true;
        } finally {
            if (!loadCompleted) {
                knownMappers.remove(type);
            }
        }
    }
}
