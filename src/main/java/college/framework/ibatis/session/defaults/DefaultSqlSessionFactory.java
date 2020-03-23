package college.framework.ibatis.session.defaults;

import college.framework.ibatis.executor.Executor;
import college.framework.ibatis.mapping.Environment;
import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.ExecutorType;
import college.framework.ibatis.session.SqlSession;
import college.framework.ibatis.session.SqlSessionFactory;
import college.framework.ibatis.transaction.Transaction;
import college.framework.ibatis.transaction.TransactionFactory;
import college.framework.ibatis.transaction.managed.ManagedTransactionFactory;
import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 11:44
 * Version:V1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration config) {
        this.configuration = config;
    }

    @Override
    public SqlSession openSession() {
        return openSessionFromDataSource(configuration.getDefaultExecutorType(), null, false);
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
        return null;
    }

    @Override
    public SqlSession openSession(Connection connection) {
        return null;
    }

    @Override
    public SqlSession openSession(TransactionIsolationLevel level) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
        return null;
    }

    @Override
    public SqlSession openSession(ExecutorType execType, Connection connection) {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
            tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
            final Executor executor = configuration.newExecutor(tx, execType);
            return new DefaultSqlSession(configuration, executor, autoCommit);
        } catch (Exception e) {
            try {
                tx.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
        } finally {

        }
    }

    //这个写法非常有意思，一般写法都是new 然后set 然后get
    private TransactionFactory getTransactionFactoryFromEnvironment(Environment environment) {
        if (environment == null || environment.getTransactionFactory() == null) {
            return new ManagedTransactionFactory();
        }
        return environment.getTransactionFactory();
    }
}
