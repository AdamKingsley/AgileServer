package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.command.TestCommand;
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
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    public void insertTest() {
        TestCommand testCommand = Mockito.mock(TestCommand.class);
        testService.insert(testCommand);
    }

    @Test
    public void findByUsernameTest() {
        testService.findByUsername("Desperate Wilder");
    }
}
