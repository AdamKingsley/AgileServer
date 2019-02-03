package cn.edu.nju.software.agile_server.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userDao;

    @Test
    public void testFind01(){
        userDao.findUserById(28L);
    }

    @Test
    public void testFind02(){
        userDao.findUserIdByOpenId("adadafagrkjijoiasjodji");
    }
}
