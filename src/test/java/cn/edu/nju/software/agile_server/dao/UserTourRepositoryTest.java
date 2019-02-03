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
public class UserTourRepositoryTest {
    @Autowired
    private UserTourRepository userTourDao;

    @Test
    public void testFind01() {
        userTourDao.findAllByTourIdAndState(1L, true);
    }

    @Test
    public void testFind02() {
        userTourDao.findAllByTourIdAndUserIdAndState(1L, 28L, true);
    }

    @Test
    public void testFind03() {
        userTourDao.findAllByUserIdAndState(28L, true);
    }
}
