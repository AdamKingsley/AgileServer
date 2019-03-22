package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.entity.User;
import cn.edu.nju.software.agile_server.form.LoginForm;
import cn.edu.nju.software.agile_server.form.UserForm;
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
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void loginTest(){
        LoginForm loginForm= Mockito.mock(LoginForm.class);
        userService.login(loginForm);
    }

    @Test
    public void create(){
        UserForm userForm = Mockito.mock(UserForm.class);
        userService.create(userForm);
    }

    @Test
    public void update(){
        UserForm userForm = Mockito.mock(UserForm.class);
        userService.update(userForm);
    }

    @Test
    public void getUserTest(){
        userService.getUser(28L);
    }
}
