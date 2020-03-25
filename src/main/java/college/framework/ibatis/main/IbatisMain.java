package college.framework.ibatis.main;


import college.framework.ibatis.session.Configuration;
import college.framework.ibatis.session.SqlSession;
import college.framework.ibatis.session.SqlSessionFactory;
import college.framework.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author: xuxianbei
 * Date: 2020/3/23
 * Time: 11:37
 * Version:V1.0
 */
public class IbatisMain {
    public static void main(String[] args) {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        Configuration configuration = new Configuration();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(configuration);
        //Class<?> boundType = Resources.classForName(namespace); 这个是ibatis做法
        configuration.addMapper(Mapper.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            User user = mapper.getUser(1);
            System.out.println(user);
//            Assert.isTrue(Objects.equals(0, user.setterCounter), "test");
//            Assert.isTrue(Objects.nonNull(user.getLazy1()));
//            Assert.isTrue(Objects.equals(1, user.setterCounter), "Should NOT load other lazy properties.");
        } finally {
            sqlSession.close();
        }
    }
}

