package cn.edu.nju.software.agile_server.dao;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TourRepositoryTest {

    @Autowired
    private TourRepository tourDao;

    @Test
    public void testFind01() {
        tourDao.findByIdAndState(1L, 0);
    }

    @Test
    public void testFind02() {
        tourDao.findByState(0);
    }

    @Test
    public void testFind03() {
        tourDao.findAllByClubIdExistsAndState(Lists.newArrayList(1L, 2L, 3L), 0);
    }
}
