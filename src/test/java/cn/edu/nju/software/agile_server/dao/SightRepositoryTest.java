package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.common.PageResult;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SightRepositoryTest {

    @Autowired
    private SightRepository sightDao;

    @Test
    public void findTest01(){
        Pageable pageable = Mockito.mock(PageRequest.class);
        sightDao.findAllByCity("320100",pageable);
    }

    @Test
    public void findTest02(){
        sightDao.findAllByCityId("320100");
    }
    @Test
    public void findTest03(){
        sightDao.findAllById(Lists.newArrayList(1L,28L,29L,30L,31L,32L));
    }
    @Test
    public void findTest04(){
        Pageable pageable = Mockito.mock(PageRequest.class);
        sightDao.findAllByCityAndNameLike("320100","夫子",pageable);
    }
}
