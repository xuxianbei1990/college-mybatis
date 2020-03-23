package college.framework.ibatis.mapping;

import college.framework.ibatis.transaction.TransactionFactory;
import lombok.Data;

import javax.sql.DataSource;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 14:21
 * Version:V1.0
 */
@Data
public class Environment {

    private TransactionFactory transactionFactory;

    private DataSource dataSource;

    public Environment(TransactionFactory transactionFactory, DataSource dataSource) {
        this.transactionFactory = transactionFactory;
        this.dataSource = dataSource;
    }
}
