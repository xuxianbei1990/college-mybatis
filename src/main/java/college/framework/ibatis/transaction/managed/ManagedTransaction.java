package college.framework.ibatis.transaction.managed;

import college.framework.ibatis.transaction.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 16:23
 * Version:V1.0
 */
@Slf4j
public class ManagedTransaction implements Transaction {
    private Connection connection;
    private final boolean closeConnection;
    private TransactionIsolationLevel level;
    private DataSource dataSource;

    public ManagedTransaction(Connection conn, boolean closeConnection) {
        this.connection = connection;
        this.closeConnection = closeConnection;
    }

    public ManagedTransaction(DataSource ds, TransactionIsolationLevel level, boolean closeConnection) {
        this.dataSource = ds;
        this.level = level;
        this.closeConnection = closeConnection;
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public Connection getConnection() throws SQLException {
        if (this.connection == null) {
            openConnection();
        }
        return this.connection;
    }

    @Override
    public Integer getTimeout() throws SQLException {
        return null;
    }

    protected void openConnection() throws SQLException {
        if (log.isDebugEnabled()) {
            log.debug("Opening JDBC Connection");
        }
        this.connection = this.dataSource.getConnection();
        if (this.level != null) {
            this.connection.setTransactionIsolation(this.level.getLevel());
        }
    }
}
