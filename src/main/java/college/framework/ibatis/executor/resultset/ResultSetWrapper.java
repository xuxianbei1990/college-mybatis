package college.framework.ibatis.executor.resultset;

import college.framework.ibatis.session.Configuration;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: xuxianbei
 * Date: 2020/3/24
 * Time: 20:31
 * Version:V1.0
 */
@Data
public class ResultSetWrapper {

    private final ResultSet resultSet;

    public ResultSetWrapper(ResultSet rs, Configuration configuration) throws SQLException {
        this.resultSet = rs;
    }

}
