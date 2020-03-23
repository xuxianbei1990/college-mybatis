package college.framework.ibatis.session;

import college.framework.ibatis.session.defaults.DefaultSqlSessionFactory;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 11:36
 * Version:V1.0
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Configuration config) {
        //源码是通过xml解析得到config。我这边就直接这么处理了
        config.init();
        return new DefaultSqlSessionFactory(config);
    }
}
