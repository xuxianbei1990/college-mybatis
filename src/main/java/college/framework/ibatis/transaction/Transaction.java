package college.framework.ibatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:20
 * Version:V1.0
 */
public interface Transaction {

    void close() throws SQLException;

    Connection getConnection() throws SQLException;

    Integer getTimeout() throws SQLException;
}
