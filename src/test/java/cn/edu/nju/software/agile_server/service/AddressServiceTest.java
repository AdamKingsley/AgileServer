package cn.edu.nju.software.agile_server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @Test
    public void testListProvinces(){
        addressService.listProvinces();
    }

    @Test
    public void testListCities(){
        addressService.listCitiesByProvinceId("320000");
    }

    @Test
    public void testListAeras(){
        addressService.listAreasByCityId("320100");
    }

}
