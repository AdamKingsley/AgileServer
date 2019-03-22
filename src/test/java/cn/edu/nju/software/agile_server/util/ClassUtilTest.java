package cn.edu.nju.software.agile_server.util;

import cn.edu.nju.software.agile_server.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClassUtilTest {


    @Test
    public void test(){
        User user = new User();
        ClassUtil.getNullPropertyNames(user);
    }
}
