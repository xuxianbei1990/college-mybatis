package college.framework.ibatis.executor.statement;

import college.framework.ibatis.executor.Executor;
import college.framework.ibatis.mapping.BoundSql;
import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.session.ResultHandler;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/3/24
 * Time: 16:01
 * Version:V1.0
 */
public class RoutingStatementHandler implements StatementHandler {

    private final StatementHandler delegate;

    public RoutingStatementHandler(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds,
                                   ResultHandler resultHandler, BoundSql boundSql) {
        switch (ms.getStatementType()) {
            case PREPARED:
                delegate = new PreparedStatementHandler(executor, ms, parameter, rowBounds, resultHandler, boundSql);
                break;
            default:
                throw new ExecutorException("Unknown statement type: " + ms.getStatementType());
        }
    }

    @Override
    public Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException {
        return delegate.prepare(connection, transactionTimeout);
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {

    }

    @Override
    public void batch(Statement statement) throws SQLException {

    }

    @Override
    public int update(Statement statement) throws SQLException {
        return 0;
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        return delegate.<E>query(statement, resultHandler);
    }


    @Override
    public <E> Cursor<E> queryCursor(Statement statement) throws SQLException {
        return null;
    }

    @Override
    public BoundSql getBoundSql() {
        return null;
    }

    @Override
    public ParameterHandler getParameterHandler() {
        return null;
    }
}
