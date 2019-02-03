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
public class UserClubRepositoryTest {

    @Autowired
    private UserClubRepository userClubDao;

    @Test
    public void testFind01() {
        userClubDao.findAllByUserIdAndState(28L, true);
    }


    @Test
    public void testFind02() {
        userClubDao.findAllByClubIdAndState(1L, true);
    }


    @Test
    public void testFind03() {
        userClubDao.findAllByClubIdAndUserIdAndState(1L, 28L, true);
    }
}
