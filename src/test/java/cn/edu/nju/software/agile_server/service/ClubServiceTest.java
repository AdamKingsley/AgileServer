package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.form.ClubCreateForm;
import cn.edu.nju.software.agile_server.form.ClubInviteForm;
import cn.edu.nju.software.agile_server.form.ClubListForm;
import cn.edu.nju.software.agile_server.form.JoinClubForm;
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
public class ClubServiceTest {
    @Autowired
    private ClubService clubService;

    @Test
    public void createClubTest() {
        ClubCreateForm clubCreateForm = Mockito.mock(ClubCreateForm.class);
        clubService.createClub(clubCreateForm);
    }

    @Test
    public void deleteClubTest() {
        clubService.deleteClub(1L);
    }

    @Test
    public void updateClubTest() {
        ClubCreateForm clubCreateForm = Mockito.mock(ClubCreateForm.class);
        clubService.updateClub(clubCreateForm);
    }

    @Test
    public void joinClubTest() {
        JoinClubForm joinClubForm = Mockito.mock(JoinClubForm.class);
        clubService.joinClub(joinClubForm);
    }

    @Test
    public void exitClubTest() {
        JoinClubForm joinClubForm = Mockito.mock(JoinClubForm.class);
        clubService.exitClub(joinClubForm);
    }

    @Test
    public void getClubDetailTest() {
        clubService.getClubDetail(12L,28L);
    }

    @Test
    public void getClubListTest() {
        ClubListForm clubListForm = Mockito.mock(ClubListForm.class);
        clubService.getClubList(clubListForm);
    }

    @Test
    public void getMyClubListTest() {
        clubService.getMyClubList(28L);
    }

    @Test
    public void saveInvitationToNotificationTest(){
        ClubInviteForm clubInviteForm = Mockito.mock(ClubInviteForm.class);
        clubService.saveInvitationToNotification(clubInviteForm);
    }

}
