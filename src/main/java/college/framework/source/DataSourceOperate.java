package college.framework.source;

import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author: xuxianbei
 * Date: 2020/3/26
 * Time: 14:02
 * Version:V1.0
 */
public class DataSourceOperate {

    public static void main(String[] args) throws SQLException {

        Properties properties = null;
        try {
            properties = Resources.getResourceAsProperties("application.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        StatementResultSet statementResultSet = new StatementResultSet();
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
                    properties.getProperty("password"));

            //执行查询
//            executeQuery(connection, statementResultSet);

            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            //首先是数据触发的。其中事务其实是一种概念性问题。
            executeUpdate(connection, statementResultSet);
            connection.rollback();

            if (statementResultSet.getResultSet() != null) {
                int col = statementResultSet.getResultSet().getMetaData().getColumnCount();
                while (statementResultSet.getResultSet().next()) {
                    for (int i = 1; i <= col; i++) {
                        System.out.println(statementResultSet.getResultSet().getString(i));
                    }
                }
                statementResultSet.getResultSet().close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            try {
                statementResultSet.getPreparedStatement().close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private static void executeQuery(Connection connection, StatementResultSet statementResultSet) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id >= ?");
        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();
        statementResultSet.setResultSet(resultSet);
        statementResultSet.setPreparedStatement(preparedStatement);
    }

    private static void executeUpdate(Connection connection, StatementResultSet statementResultSet) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  users set name = ? where id = ?");
        preparedStatement.setString(1, "xxb2");
        preparedStatement.setInt(2, 1);
        boolean resultSet = preparedStatement.execute();
        statementResultSet.setResultSet(preparedStatement.getResultSet());
        statementResultSet.setPreparedStatement(preparedStatement);
    }
}
