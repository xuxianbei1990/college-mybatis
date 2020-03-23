package college.framework.ibatis.transaction.managed;

import college.framework.ibatis.transaction.Transaction;
import college.framework.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:26
 * Version:V1.0
 */
public class ManagedTransactionFactory implements TransactionFactory {

    private boolean closeConnection = true;

    @Override
    public Transaction newTransaction(Connection conn) {
        return new ManagedTransaction(conn, closeConnection);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return null;
    }
}
