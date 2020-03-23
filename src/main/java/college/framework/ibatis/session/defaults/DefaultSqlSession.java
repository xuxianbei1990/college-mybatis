package college.framework.ibatis.session.defaults;

import college.framework.ibatis.executor.Executor;
import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.SqlSession;

import java.sql.Connection;

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
    public <T> T getMapper(Class<T> type) {
        return configuration.<T>getMapper(type, this);
    }

    @Override
    public void close() {

    }
}
