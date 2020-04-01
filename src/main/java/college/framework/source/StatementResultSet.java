package college.framework.source;

import lombok.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: xuxianbei
 * Date: 2020/3/26
 * Time: 14:26
 * Version:V1.0
 */
@Data
public class StatementResultSet {
   private PreparedStatement preparedStatement;
   private ResultSet resultSet;
}
