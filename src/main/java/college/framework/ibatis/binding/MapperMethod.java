package college.framework.ibatis.binding;

import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.SqlSession;
import org.apache.ibatis.mapping.SqlCommandType;

import java.lang.reflect.Method;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 18:03
 * Version:V1.0
 */
public class MapperMethod {

    private final SqlCommand command;
    private final MethodSignature method;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration config) {
        this.command = new SqlCommand(config, mapperInterface, method);
        this.method = new MethodSignature(config, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object result;
        switch (command.getType()) {
            case SELECT:
//                if (method)
        }
        return null;
    }
    public static class SqlCommand {

        private final SqlCommandType type;

        public SqlCommand(Configuration config, Class<?> mapperInterface, Method method) {
           type = SqlCommandType.SELECT;
        }

        public SqlCommandType getType() {
            return type;
        }

    }
    public static class MethodSignature {

        public MethodSignature(Configuration config, Class<?> mapperInterface, Method method) {

        }
    }

}
