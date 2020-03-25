package college.framework.ibatis.mapping;

import college.framework.ibatis.session.Configuration;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.scripting.LanguageDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/3/24
 * Time: 15:18
 * Version:V1.0
 */
public class MappedStatement {

    private Configuration configuration;

    //默认  StatementType.PREPARED
    private StatementType statementType = StatementType.PREPARED;

    private List<ResultMap> resultMaps = new ArrayList();

    private SqlSource sqlSource;
    private LanguageDriver lang;
    private String id;
    private Log statementLog;

    public BoundSql getBoundSql(Object parameterObject) {
        BoundSql boundSql = new BoundSql("select * from users where id = 1");
        return boundSql;
    }

    public String getId() {
        return id;
    }

    public Log getStatementLog() {
        return statementLog;
    }

    public StatementType getStatementType() {
        return statementType;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public LanguageDriver getLang() {
        return lang;
    }

    public MappedStatement(String id) {
        this.id = id;
    }

    public List<ResultMap> getResultMaps() {
        return resultMaps;
    }

}
