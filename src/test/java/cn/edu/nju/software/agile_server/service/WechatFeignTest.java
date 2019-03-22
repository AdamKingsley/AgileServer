package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.config.WechatConfig;
import cn.edu.nju.software.agile_server.service.feign.WechatFeign;
import jodd.util.RandomString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WechatFeignTest {

    @Autowired
    private WechatFeign wechatFeign;
    @Autowired
    private WechatConfig wechatConfig;

    @Test
    public void login() {
        String code = RandomString.getInstance().randomAlphaNumeric(15);
        wechatFeign.login(wechatConfig.getAppid(), wechatConfig.getSecret(), code, wechatConfig.getGrantType());
    }

}
