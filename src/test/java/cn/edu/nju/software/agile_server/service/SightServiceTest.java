package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.entity.Sight;
import cn.edu.nju.software.agile_server.form.SightForm;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SightServiceTest {

    @Autowired
    private SightService sightService;

    @Test
    public void addTest() {
        SightForm sightForm = Mockito.mock(SightForm.class);
        sightService.addSight(sightForm);
    }

    @Test
    public void addBatchTest() {
        SightForm sightForm = Mockito.mock(SightForm.class);
        sightService.addSightBatch(Lists.newArrayList(sightForm));
    }

    @Test
    public void findSightsTest() {
        sightService.findSights("320100", Lists.newArrayList("name"), 1, 10);
    }

    @Test
    public void sightDetailTest() {
        sightService.sightDetail(32L);
    }

    @Test
    public void findSightsTest2() {
        sightService.findSights("320100", Lists.newArrayList("name"), 1, 10, "总统");
    }

    @Test
    public void findAllByCityIdTest(){
        sightService.findAllSightsByCityId("320100");
    }
}
