package college.framework.ibatis.session;

import java.sql.Connection;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 11:47
 * Version:V1.0
 */
public interface SqlSession {

    Connection getConnection();

    Configuration getConfiguration();

    <T> T getMapper(Class<T> type);

    void close();
}
