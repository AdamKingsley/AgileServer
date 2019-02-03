package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.form.LoginForm;
import cn.edu.nju.software.agile_server.form.UserForm;
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
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginTest() throws Exception {
        LoginForm loginForm = Mockito.mock(LoginForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/login").content(mapper.writeValueAsString(loginForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }


    @Test
    public void createTest() throws Exception {
        UserForm userForm = Mockito.mock(UserForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/create").content(mapper.writeValueAsString(userForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void updateTest() throws Exception {
        UserForm userForm = Mockito.mock(UserForm.class);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/update").content(mapper.writeValueAsString(userForm)).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }

    @Test
    public void getUserByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/28")
                .accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        String contentbody = result.getResponse().getContentAsString();
        System.out.println(contentbody);
    }
}
