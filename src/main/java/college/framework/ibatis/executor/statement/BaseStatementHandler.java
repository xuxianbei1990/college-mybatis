package college.framework.ibatis.executor.statement;


import college.framework.ibatis.executor.Executor;
import college.framework.ibatis.executor.parameter.ParameterHandler;
import college.framework.ibatis.mapping.BoundSql;
import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.ResultHandler;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: xuxianbei
 * Date: 2020/3/24
 * Time: 16:19
 * Version:V1.0
 */
public abstract class BaseStatementHandler implements StatementHandler {

    protected final ResultSetHandler resultSetHandler;
    protected final Configuration configuration;
    protected final ParameterHandler parameterHandler;
    protected BoundSql boundSql;

    protected BaseStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject,
                                   RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        this.boundSql = boundSql;
        this.configuration = mappedStatement.getConfiguration();
        this.parameterHandler = configuration.newParameterHandler(mappedStatement, parameterObject, boundSql);
        this.resultSetHandler = configuration.newResultSetHandler(executor, mappedStatement, rowBounds, parameterHandler, resultHandler, boundSql);
    }


    @Override
    public Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException {
        Statement statement = null;
        try {
            statement = instantiateStatement(connection);
            return statement;
        } catch (SQLException e) {
            closeStatement(statement);
            throw e;
        } catch (Exception e) {
            closeStatement(statement);
            throw new ExecutorException("Error preparing statement.  Cause: " + e, e);
        }
    }

    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;

    protected void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //ignore
        }
    }
}
