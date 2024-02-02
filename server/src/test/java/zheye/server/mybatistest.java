/*
package zheye.server;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import zheye.server.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class mybatistest {
    @Test
    void testMybatis() {
        try {
            InputStream inputStream = null;
            String resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession=sqlSessionFactory.openSession();
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<Integer> result=userMapper.findbyid();
            sqlSession.close();
            System.out.print("result:"+result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
*/
