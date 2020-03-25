package college.framework.ibatis.scripting.defaults;

import college.framework.ibatis.executor.parameter.ParameterHandler;
import college.framework.ibatis.mapping.BoundSql;
import college.framework.ibatis.mapping.MappedStatement;
import college.framework.ibatis.session.Configuration;

/**
 * @author: xuxianbei
 * Date: 2020/3/24
 * Time: 17:19
 * Version:V1.0
 */
public class DefaultParameterHandler implements ParameterHandler {

    private final MappedStatement mappedStatement;
    private final Object parameterObject;
    private final BoundSql boundSql;
    private final Configuration configuration;

    public DefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        this.mappedStatement = mappedStatement;
        this.configuration = mappedStatement.getConfiguration();
//        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
    }
}
