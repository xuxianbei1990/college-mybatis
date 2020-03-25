package college.framework.ibatis.executor;

import college.framework.ibatis.cache.impl.PerpetualCache;
import college.framework.ibatis.mapping.BoundSql;
import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.ResultHandler;
import college.framework.ibatis.transaction.Transaction;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.jdbc.ConnectionLogger;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:45
 * Version:V1.0
 */
public abstract class BaseExecutor implements Executor {

    protected Transaction transaction;
    protected Configuration configuration;
    protected int queryStack;

    protected Executor wrapper;

    protected PerpetualCache localCache;
    protected PerpetualCache localOutputParameterCache;


    protected BaseExecutor(Configuration configuration, Transaction transaction) {
        this.transaction = transaction;
        this.configuration = configuration;
        this.wrapper = this;
    }

    @Override
    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {
        BoundSql boundSql = ms.getBoundSql(parameter);
        CacheKey key = createCacheKey(ms, parameter, rowBounds, boundSql);
        return query(ms, parameter, rowBounds, resultHandler, key, boundSql);
    }

    public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException {
        List<E> list;
        list = queryFromDatabase(ms, parameter, rowBounds, resultHandler, key, boundSql);
        return list;
    }

    @Override
    public CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql) {
        CacheKey cacheKey = new CacheKey();
//        cacheKey.update(ms.getId());
//        cacheKey.update(rowBounds.getOffset());
//        cacheKey.update(rowBounds.getLimit());
//        cacheKey.update(boundSql.getSql());
//        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
//        TypeHandlerRegistry typeHandlerRegistry = ms.getConfiguration().getTypeHandlerRegistry();
//        // mimic DefaultParameterHandler logic
//        for (ParameterMapping parameterMapping : parameterMappings) {
//            if (parameterMapping.getMode() != ParameterMode.OUT) {
//                Object value;
//                String propertyName = parameterMapping.getProperty();
//                if (boundSql.hasAdditionalParameter(propertyName)) {
//                    value = boundSql.getAdditionalParameter(propertyName);
//                } else if (parameterObject == null) {
//                    value = null;
//                } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
//                    value = parameterObject;
//                } else {
//                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
//                    value = metaObject.getValue(propertyName);
//                }
//                cacheKey.update(value);
//            }
//        }
//        if (configuration.getEnvironment() != null) {
//            // issue #176
//            cacheKey.update(configuration.getEnvironment().getId());
//        }
        return cacheKey;
    }

    private <E> List<E> queryFromDatabase(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException {
        List<E> list;
        //这个写法确实比较有意思
//        localCache.putObject(key, EXECUTION_PLACEHOLDER);
        try {
            list = doQuery(ms, parameter, rowBounds, resultHandler, boundSql);
        } finally {
//            localCache.removeObject(key);
        }
//        localCache.putObject(key, list);
        if (ms.getStatementType() == StatementType.CALLABLE) {
            localOutputParameterCache.putObject(key, parameter);
        }
        return list;
    }

    protected void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // ignore
            }
        }
    }

    protected Connection getConnection(Log statementLog) throws SQLException {
        Connection connection = transaction.getConnection();
        if (statementLog != null && statementLog.isDebugEnabled()) {
            return ConnectionLogger.newInstance(connection, statementLog, queryStack);
        } else {
            return connection;
        }
    }

    protected abstract <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql)
            throws SQLException;
}
