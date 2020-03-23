package college.framework.ibatis.session;

import college.framework.ibatis.binding.MapperRegistry;
import college.framework.ibatis.datasource.unpooled.UnpooledDataSource;
import college.framework.ibatis.executor.Executor;
import college.framework.ibatis.executor.SimpleExecutor;
import college.framework.ibatis.mapping.Environment;
import college.framework.ibatis.transaction.Transaction;
import college.framework.ibatis.transaction.managed.ManagedTransactionFactory;
import lombok.Data;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.plugin.InterceptorChain;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 11:45
 * Version:V1.0
 */
@Data
public class Configuration {

    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);

    protected ExecutorType defaultExecutorType = ExecutorType.SIMPLE;

    protected Environment environment;

    protected final InterceptorChain interceptorChain = new InterceptorChain();

    public Executor newExecutor(Transaction transaction, ExecutorType executorType) {
        executorType = executorType == null ? defaultExecutorType : executorType;
        executorType = executorType == null ? ExecutorType.SIMPLE : executorType;
        Executor executor;
        executor = new SimpleExecutor(this, transaction);
        executor = (Executor) interceptorChain.pluginAll(executor);
        return executor;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public void init() {
        Properties properties = null;
        try {
            properties = Resources.getResourceAsProperties("application.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        environment = new Environment(new ManagedTransactionFactory(), new UnpooledDataSource(
                properties.getProperty("driverClassName"),
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password")));

    }
}
