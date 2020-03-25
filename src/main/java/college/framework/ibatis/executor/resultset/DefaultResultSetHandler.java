package college.framework.ibatis.executor.resultset;

import college.framework.ibatis.executor.Executor;
import college.framework.ibatis.executor.parameter.ParameterHandler;
import college.framework.ibatis.main.User;
import college.framework.ibatis.mapping.BoundSql;
import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.mapping.ResultMap;
import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.ResultHandler;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.RowBounds;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/3/24
 * Time: 17:13
 * Version:V1.0
 */
public class DefaultResultSetHandler implements ResultSetHandler {

    private final Configuration configuration;
    private final MappedStatement mappedStatement;

    public DefaultResultSetHandler(Executor executor, MappedStatement mappedStatement, ParameterHandler parameterHandler, ResultHandler resultHandler, BoundSql boundSql, RowBounds rowBounds) {
        this.configuration = mappedStatement.getConfiguration();
        this.mappedStatement = mappedStatement;
    }

    @Override
    public List<Object> handleResultSets(Statement stmt) throws SQLException {
        ResultSetWrapper rsw = getFirstResultSet(stmt);
        List<ResultMap> resultMaps = mappedStatement.getResultMaps();
        final List<Object> multipleResults = new ArrayList<Object>();
        int resultSetCount = 0;
//        while (rsw != null) {
        handleResultSet(rsw, null, multipleResults, null);
//            rsw = getNextResultSet(stmt);
//            cleanUpAfterHandlingResultSet();
        resultSetCount++;
//        }

        return multipleResults;
    }

    private void handleResultSet(ResultSetWrapper rsw, ResultMap resultMap, List<Object> multipleResults, ResultMapping parentMapping) throws SQLException {
        ResultSet resultSet = rsw.getResultSet();
        while (resultSet.next()) {
            User user = new User();
            int i = resultSet.getInt("id");
            String value = resultSet.getString("name");
            user.setId(i);
            user.setName(value);
            multipleResults.add(user);
            System.out.println(i + value);
        }
    }

    @Override
    public <E> Cursor<E> handleCursorResultSets(Statement stmt) throws SQLException {
        return null;
    }

    @Override
    public void handleOutputParameters(CallableStatement cs) throws SQLException {

    }

    private ResultSetWrapper getFirstResultSet(Statement stmt) throws SQLException {
        ResultSet rs = stmt.getResultSet();
        while (rs == null) {
            // move forward to get the first resultset in case the driver
            // doesn't return the resultset as the first result (HSQLDB 2.1)
            if (stmt.getMoreResults()) {
                rs = stmt.getResultSet();
            } else {
                if (stmt.getUpdateCount() == -1) {
                    // no more results. Must be no resultset
                    break;
                }
            }
        }
        return rs != null ? new ResultSetWrapper(rs, configuration) : null;
    }
}
