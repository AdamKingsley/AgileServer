package cn.edu.nju.software.agile_server.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AddressRepositoryTest {


    @Resource
    private AddressRepository addressDao;

    @Test
    public void findProvincesTest(){
        addressDao.findProvinces();
    }

    @Test
    public void findCitiesTest(){
        addressDao.findCitiesByProvinceId("320000");
    }

    @Test
    public void findAreasTest(){
        addressDao.findAreasByCityId("320100");
    }
}
