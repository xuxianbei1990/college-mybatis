package college.framework.ibatis.executor;

import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.transaction.Transaction;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:45
 * Version:V1.0
 */
public abstract class BaseExecutor implements Executor {

    protected Transaction transaction;
    protected Configuration configuration;

    protected BaseExecutor(Configuration configuration, Transaction transaction) {
        this.transaction = transaction;
        this.configuration = configuration;
    }
}
