package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.form.ClubCreateForm;
import cn.edu.nju.software.agile_server.form.ClubListForm;
import cn.edu.nju.software.agile_server.form.JoinClubForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ClubControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createClubTest() throws Exception {
        ClubCreateForm  clubCreateForm = Mockito.mock(ClubCreateForm.class);
        ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/club/create").content(mapper.writeValueAsString(clubCreateForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }


    @Test
    public void deleteClubTest() throws Exception {
        ClubCreateForm  clubCreateForm = Mockito.mock(ClubCreateForm.class);
        ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/club/delete").content(mapper.writeValueAsString(clubCreateForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }


    @Test
    public void updateClubTest() throws Exception {
        ClubCreateForm  clubCreateForm = Mockito.mock(ClubCreateForm.class);
        ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/club/update").content(mapper.writeValueAsString(clubCreateForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }


    @Test
    public void joinClubTest() throws Exception {
        JoinClubForm joinClubForm = Mockito.mock(JoinClubForm.class);
        ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/club/join").content(mapper.writeValueAsString(joinClubForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void exitClubTest() throws Exception {
        JoinClubForm joinClubForm = Mockito.mock(JoinClubForm.class);
        ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/club/exit").content(mapper.writeValueAsString(joinClubForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void detailClubTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/club/detail").param("clubId","1").param("userId","113").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void listClubTest() throws Exception {
        ClubListForm clubListForm = Mockito.mock(ClubListForm.class);
        ObjectMapper mapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/club/list").content(mapper.writeValueAsString(clubListForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }


    @Test
    public void myClubListTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/club/my").param("userId","29").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void invitationToNotificationTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/club/invite").param("invitedId","26").param("senderId","113").param("clubId","1").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void isInTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/club/check/26/1").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }
}
