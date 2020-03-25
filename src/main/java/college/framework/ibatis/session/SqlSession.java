package college.framework.ibatis.session;

import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.List;

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

    <E> List<E> selectList(String statement, Object parameter);

    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds);

    <T> T selectOne(String statement, Object parameter);
}
