package college.framework.ibatis.transaction.managed;

import college.framework.ibatis.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 16:23
 * Version:V1.0
 */
public class ManagedTransaction implements Transaction {
    private Connection connection;
    private final boolean closeConnection;

    public ManagedTransaction(Connection conn, boolean closeConnection) {
        this.connection = connection;
        this.closeConnection = closeConnection;
    }

    @Override
    public void close() throws SQLException {

    }
}
