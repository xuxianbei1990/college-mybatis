package college.framework.ibatis.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:24
 * Version:V1.0
 * 所以Connection 是官方提供一套api对接。
 */
public interface TransactionFactory {

    Transaction newTransaction(Connection conn);

    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);
}
