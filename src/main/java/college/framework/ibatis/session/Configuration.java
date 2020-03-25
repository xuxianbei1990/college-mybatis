package college.framework.ibatis.session;

import college.framework.ibatis.binding.MapperRegistry;
import college.framework.ibatis.datasource.unpooled.UnpooledDataSource;
import college.framework.ibatis.executor.Executor;
import college.framework.ibatis.executor.SimpleExecutor;
import college.framework.ibatis.executor.parameter.ParameterHandler;
import college.framework.ibatis.executor.resultset.DefaultResultSetHandler;
import college.framework.ibatis.executor.statement.RoutingStatementHandler;
import college.framework.ibatis.executor.statement.StatementHandler;
import college.framework.ibatis.mapping.BoundSql;
import college.framework.ibatis.mapping.Environment;
import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.scripting.defaults.DefaultParameterHandler;
import college.framework.ibatis.transaction.Transaction;
import college.framework.ibatis.transaction.managed.ManagedTransactionFactory;
import lombok.Data;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.plugin.InterceptorChain;
import org.apache.ibatis.session.RowBounds;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 11:45
 * Version:V1.0
 */
@Data
public class Configuration {

    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected final Map<String, MappedStatement> mappedStatements = new StrictMap<MappedStatement>("Mapped Statements collection");

    protected ExecutorType defaultExecutorType = ExecutorType.SIMPLE;

    protected Environment environment;

    protected final InterceptorChain interceptorChain = new InterceptorChain();

    public Executor newExecutor(Transaction transaction, ExecutorType executorType) {
        executorType = executorType == null ? defaultExecutorType : executorType;
        executorType = executorType == null ? ExecutorType.SIMPLE : executorType;
        Executor executor;
        executor = new SimpleExecutor(this, transaction);
        executor = (Executor) interceptorChain.pluginAll(executor);
        return executor;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public void init() {
        Properties properties = null;
        try {
            properties = Resources.getResourceAsProperties("application.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        environment = new Environment(new ManagedTransactionFactory(), new UnpooledDataSource(
                properties.getProperty("driverClassName"),
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password")));

    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler = (ParameterHandler) interceptorChain.pluginAll(parameterHandler);
        return parameterHandler;
    }

    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds, ParameterHandler parameterHandler,
                                                ResultHandler resultHandler, BoundSql boundSql) {
        ResultSetHandler resultSetHandler = new DefaultResultSetHandler(executor, mappedStatement, parameterHandler, resultHandler, boundSql, rowBounds);
        resultSetHandler = (ResultSetHandler) interceptorChain.pluginAll(resultSetHandler);
        return resultSetHandler;
    }

    public MappedStatement getMappedStatement(String id) {
        return this.getMappedStatement(id, true);
    }

    public MappedStatement getMappedStatement(String id, boolean validateIncompleteStatements) {
        if (validateIncompleteStatements) {
            buildAllStatements();
        }
        return mappedStatements.get(id);
    }

    public boolean hasStatement(String statementName) {
        return hasStatement(statementName, true);
    }

    public boolean hasStatement(String statementName, boolean validateIncompleteStatements) {
        if (validateIncompleteStatements) {
            buildAllStatements();
        }
        return mappedStatements.containsKey(statementName);
    }

    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        StatementHandler statementHandler = new RoutingStatementHandler(executor, mappedStatement, parameterObject, rowBounds, resultHandler, boundSql);
        statementHandler = (StatementHandler) interceptorChain.pluginAll(statementHandler);
        return statementHandler;
    }


    protected void buildAllStatements() {
        MappedStatement mappedStatement = new MappedStatement("college.framework.ibatis.main.Mapper.getUser");
        mappedStatement.setConfiguration(this);
        mappedStatements.putIfAbsent(mappedStatement.getId(), mappedStatement);
    }

    protected static class StrictMap<V> extends HashMap<String, V> {

        private static final long serialVersionUID = -4950446264854982944L;
        private final String name;

        public StrictMap(String name, int initialCapacity, float loadFactor) {
            super(initialCapacity, loadFactor);
            this.name = name;
        }

        public StrictMap(String name, int initialCapacity) {
            super(initialCapacity);
            this.name = name;
        }

        public StrictMap(String name) {
            super();
            this.name = name;
        }

        public StrictMap(String name, Map<String, ? extends V> m) {
            super(m);
            this.name = name;
        }

        @SuppressWarnings("unchecked")
        public V put(String key, V value) {
            if (containsKey(key)) {
                throw new IllegalArgumentException(name + " already contains value for " + key);
            }
            if (key.contains(".")) {
                final String shortKey = getShortName(key);
                if (super.get(shortKey) == null) {
                    super.put(shortKey, value);
                } else {
                    super.put(shortKey, (V) new Ambiguity(shortKey));
                }
            }
            return super.put(key, value);
        }

        public V get(Object key) {
            V value = super.get(key);
            if (value == null) {
                throw new IllegalArgumentException(name + " does not contain value for " + key);
            }
            if (value instanceof Ambiguity) {
                throw new IllegalArgumentException(((Ambiguity) value).getSubject() + " is ambiguous in " + name
                        + " (try using the full name including the namespace, or rename one of the entries)");
            }
            return value;
        }

        private String getShortName(String key) {
            final String[] keyParts = key.split("\\.");
            return keyParts[keyParts.length - 1];
        }

        protected static class Ambiguity {
            final private String subject;

            public Ambiguity(String subject) {
                this.subject = subject;
            }

            public String getSubject() {
                return subject;
            }
        }
    }
}
