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
public class ClubRepositoryTest {
    @Autowired
    private ClubRepository clubDao;

    @Test
    public void findTest1() {
        clubDao.findByIdAndState(1L, 0);
    }

    @Test
    public void findTest2() {
        clubDao.findByState(0);
    }

    @Test
    public void findTest3() {
        clubDao.findAllByOwnerIdExistsAndState(Lists.newArrayList(1L, 28L, 29L), 0);
    }

}