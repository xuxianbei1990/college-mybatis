package college.framework.ibatis.executor;


import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.transaction.Transaction;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:44
 * Version:V1.0
 */
public class SimpleExecutor extends BaseExecutor {
    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }
}
