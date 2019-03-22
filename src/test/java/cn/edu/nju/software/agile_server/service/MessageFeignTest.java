package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.config.MessageConfig;
import cn.edu.nju.software.agile_server.service.feign.MessageFeign;
import cn.edu.nju.software.agile_server.service.feign.feign_response.MessageFeignResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MessageFeignTest {
    @Autowired
    private MessageFeign messageFeign;
    @Autowired
    private MessageConfig messageConfig;
    @Test
    public void test(){
        StringBuffer stringBuffer = new StringBuffer().append("#code#=").append("123456");
        String tpl_value = stringBuffer.toString();
        messageFeign.send("15365196627", messageConfig.getTplId(), tpl_value, messageConfig.getAppKey());
    }
}
