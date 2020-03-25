package college.framework.ibatis.session.defaults;

import college.framework.ibatis.executor.Executor;
import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.SqlSession;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:51
 * Version:V1.0
 */
public class DefaultSqlSession implements SqlSession {
    private final Configuration configuration;
    private final Executor executor;
    private boolean dirty;
    private final boolean autoCommit;

    public DefaultSqlSession(Configuration configuration, Executor executor, boolean autoCommit) {
        this.configuration = configuration;
        this.executor = executor;
        this.dirty = false;
        this.autoCommit = autoCommit;
    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.<T>getMapper(type, this);
    }

    @Override
    public void close() {

    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        return this.selectList(statement, parameter, RowBounds.DEFAULT);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        try {
            MappedStatement ms = configuration.getMappedStatement(statement);
            return executor.query(ms, wrapCollection(parameter), rowBounds, Executor.NO_RESULT_HANDLER);
        } catch (Exception e) {
            throw ExceptionFactory.wrapException("Error querying database.  Cause: " + e, e);
        } finally {
            ErrorContext.instance().reset();
        }
    }

    public <T> T selectOne(String statement, Object parameter) {
        List<T> list = this.<T>selectList(statement, parameter);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    private Object wrapCollection(final Object object) {
        if (object instanceof Collection) {
            StrictMap<Object> map = new StrictMap<Object>();
            map.put("collection", object);
            if (object instanceof List) {
                map.put("list", object);
            }
            return map;
        } else if (object != null && object.getClass().isArray()) {
            StrictMap<Object> map = new StrictMap<Object>();
            map.put("array", object);
            return map;
        }
        return object;
    }

    public static class StrictMap<V> extends HashMap<String, V> {

        private static final long serialVersionUID = -5741767162221585340L;

        @Override
        public V get(Object key) {
            if (!super.containsKey(key)) {
                throw new BindingException("Parameter '" + key + "' not found. Available parameters are " + this.keySet());
            }
            return super.get(key);
        }

    }
}
