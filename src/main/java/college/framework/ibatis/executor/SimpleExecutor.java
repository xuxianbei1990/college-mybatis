package college.framework.ibatis.executor;


import college.framework.ibatis.executor.statement.StatementHandler;
import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.ResultHandler;
import college.framework.ibatis.transaction.Transaction;
import org.apache.ibatis.logging.Log;
import college.framework.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:44
 * Version:V1.0
 */
public class SimpleExecutor extends BaseExecutor {
    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        //这里的Statement就是java jdk sql 提供的规范
        Statement stmt = null;
        try {
            Configuration configuration = ms.getConfiguration();
            //RoutingStatementHandler
            StatementHandler handler = configuration.newStatementHandler(wrapper, ms, parameter, rowBounds, resultHandler, boundSql);
            stmt = prepareStatement(handler, ms.getStatementLog());
            return handler.<E>query(stmt, resultHandler);
        } finally {
            closeStatement(stmt);
        }
    }

    private Statement prepareStatement(StatementHandler handler, Log statementLog) throws SQLException {
        Statement stmt;
        Connection connection = getConnection(statementLog);
        stmt = handler.prepare(connection, transaction.getTimeout());
        handler.parameterize(stmt);
        return stmt;
    }



}
