package cn.edu.nju.software.agile_server.service.impl;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.config.WechatConfig;
import cn.edu.nju.software.agile_server.form.LoginForm;
import cn.edu.nju.software.agile_server.service.UserService;
import cn.edu.nju.software.agile_server.service.feign.WechatFeign;
import cn.edu.nju.software.agile_server.service.feign.feign_response.LoginFeignResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private WechatFeign wechatFeign;
    @Resource
    private WechatConfig wechatConfig;

    @Override
    public Result login(LoginForm loginForm) {
        String loginFeignResponse = wechatFeign.login(wechatConfig.getAppid(),
                wechatConfig.getSecret(), loginForm.getCode(), wechatConfig.getGrantType());
        return Result.success().withData(loginFeignResponse);
    }
}
