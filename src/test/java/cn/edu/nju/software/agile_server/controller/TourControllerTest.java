package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.form.JoinTourForm;
import cn.edu.nju.software.agile_server.form.TourCreateForm;
import cn.edu.nju.software.agile_server.form.TourListForm;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class TourControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createtourTest() throws Exception {
        TourCreateForm tourCreateForm = Mockito.mock(TourCreateForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/tour/create").content(mapper.writeValueAsString(tourCreateForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }


    @Test
    public void deletetourTest() throws Exception {
        TourCreateForm tourCreateForm = Mockito.mock(TourCreateForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/tour/delete").content(mapper.writeValueAsString(tourCreateForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }


    @Test
    public void updatetourTest() throws Exception {
        TourCreateForm tourCreateForm = Mockito.mock(TourCreateForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/tour/update").content(mapper.writeValueAsString(tourCreateForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }


    @Test
    public void jointourTest() throws Exception {
        JoinTourForm jointourForm = Mockito.mock(JoinTourForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/tour/join").content(mapper.writeValueAsString(jointourForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void exittourTest() throws Exception {
        JoinTourForm jointourForm = Mockito.mock(JoinTourForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/tour/exit").content(mapper.writeValueAsString(jointourForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void detailtourTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/tour/detail").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void listtourTest() throws Exception {
        TourListForm tourListForm = Mockito.mock(TourListForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/tour/join").content(mapper.writeValueAsString(tourListForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }


    @Test
    public void mytourListTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/tour/my").param("userId", "29").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }
}
