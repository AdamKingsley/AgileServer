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
public class TestRepositoryTest {
    @Autowired
    private TestRepository testDao;

    @Test
    public void test01(){
        testDao.findByUsername("Desperate Wilder");
    }
}
