package college;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//挑战功能扩展mybatis插件。可以查多张表数据，
//适配方式实现
@SpringBootApplication
@MapperScan("/college/mapper")
//@MapperScan("/college/tk/mapper") tk 会把表名改为大写，而在Linux mysql表名是大小写敏感的
public class CollegeMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeMybatisApplication.class, args);
	}

}
