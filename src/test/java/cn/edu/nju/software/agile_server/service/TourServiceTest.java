package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.form.TourCreateForm;
import cn.edu.nju.software.agile_server.form.TourListForm;
import cn.edu.nju.software.agile_server.form.JoinTourForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TourServiceTest {
    @Autowired
    private TourService tourService;

    @Test
    public void createTourTest() {
        TourCreateForm tourCreateForm = Mockito.mock(TourCreateForm.class);
        tourService.createTour(tourCreateForm);
    }

    @Test
    public void deleteTourTest() {
        tourService.deleteTour(1L);
    }

    @Test
    public void updateTourTest() {
        TourCreateForm tourCreateForm = Mockito.mock(TourCreateForm.class);
        tourService.updateTour(tourCreateForm);
    }

    @Test
    public void joinTourTest() {
        JoinTourForm joinTourForm = Mockito.mock(JoinTourForm.class);
        tourService.joinTour(joinTourForm);
    }

    @Test
    public void exitTourTest() {
        JoinTourForm joinTourForm = Mockito.mock(JoinTourForm.class);
        tourService.exitTour(joinTourForm);
    }

    @Test
    public void getTourDetailTest() {
        tourService.getTourDetail(12L,28L);
    }

    @Test
    public void getTourListTest() {
        TourListForm tourListForm = Mockito.mock(TourListForm.class);
        tourService.getTourList(tourListForm);
    }


    @Test
    public void getMyTourListTest() {
        tourService.getMyTourList(28L);
    }
}
