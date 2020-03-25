package college.framework.ibatis.executor;

import college.framework.ibatis.mapping.BoundSql;
import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.session.ResultHandler;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:42
 * Version:V1.0
 */
public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;

    CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql);

}
