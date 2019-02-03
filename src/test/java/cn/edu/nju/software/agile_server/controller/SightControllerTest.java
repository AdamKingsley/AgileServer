package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.form.SightForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SightControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void addSight() throws Exception {
        SightForm sightForm = Mockito.mock(SightForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/sight").content(mapper.writeValueAsString(sightForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void addSightBatch() throws Exception {
        SightForm sightForm = Mockito.mock(SightForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/sight/batch")
                .content(mapper.writeValueAsString(Lists.newArrayList(sightForm)))
                .accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }


    @Test
    public void findSightsByCityIdTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sight/320106").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void detailTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sight/detail/32").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void findSightsByCityIdAnotherTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sight/match/320106").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void addByCityId() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sight/all/320106").accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }



}
