package college.framework.ibatis.executor.statement;


import college.framework.ibatis.executor.Executor;
import college.framework.ibatis.mapping.BoundSql;
import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.session.ResultHandler;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/3/24
 * Time: 16:19
 * Version:V1.0
 */
public class PreparedStatementHandler extends BaseStatementHandler {


    public PreparedStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameter, rowBounds, resultHandler, boundSql);
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
        PreparedStatement ps = (PreparedStatement) statement;
        ps.executeQuery(this.getBoundSql().getSql());
        return resultSetHandler.<E> handleResultSets(ps);
    }

    @Override
    public <E> Cursor<E> queryCursor(Statement statement) throws SQLException {
        return null;
    }

    @Override
    public BoundSql getBoundSql() {
        return boundSql;
    }

    @Override
    public ParameterHandler getParameterHandler() {
        return null;
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        String sql = boundSql.getSql();
        return connection.prepareStatement(sql);
    }
}
