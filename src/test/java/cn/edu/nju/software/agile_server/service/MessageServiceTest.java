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
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void test(){
        messageService.sendVcodeMessage("15365196627");
    }
}
