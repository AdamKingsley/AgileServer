package cn.edu.nju.software.agile_server.service.impl;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.config.WechatConfig;
import cn.edu.nju.software.agile_server.dao.UserRepository;
import cn.edu.nju.software.agile_server.entity.User;
import cn.edu.nju.software.agile_server.form.LoginForm;
import cn.edu.nju.software.agile_server.form.UserForm;
import cn.edu.nju.software.agile_server.service.UserService;
import cn.edu.nju.software.agile_server.service.feign.WechatFeign;
import cn.edu.nju.software.agile_server.service.feign.feign_response.LoginFeignResponse;
import cn.edu.nju.software.agile_server.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private WechatFeign wechatFeign;
    @Resource
    private WechatConfig wechatConfig;
    @Resource
    private UserRepository userDao;

    @Override
    public Result login(LoginForm loginForm) {
        LoginFeignResponse loginFeignResponse = wechatFeign.login(wechatConfig.getAppid(),
                wechatConfig.getSecret(), loginForm.getCode(), wechatConfig.getGrantType());
        if (StringUtils.isEmpty(loginFeignResponse.getOpenid())) {
            return Result.error().message("获取用户openId失败！");
        }
        Long userId = userDao.findUserIdByOpenId(loginFeignResponse.getOpenid());
        //设置如果初始化过用户信息，那么就能找到userId
        // 如果找不到的话userId就为空，这对前端是否进入初始化设置信息界面，起到了判断作用
        loginFeignResponse.setUserid(userId);
        return Result.success().withData(loginFeignResponse);
    }

    @Override
    public Result getUser(Long userid){
        User user = userDao.findUserById(userid);
        if(user == null){
            System.out.println("用户id错误！");
            return Result.error().message("用户id错误！");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return Result.success().withData(userVO).message("获取用户信息成功!");
    }

    @Override
    public Result create(UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        User saved = userDao.save(user);
        return Result.success().message("创建用户，保存用户信息成功！").withData(saved.getId());
    }

    @Override
    public Result update(UserForm userForm) {
        if (StringUtils.isEmpty(userForm.getId())) {
            Result.error().message("更新的用户指定的对应id为空！");
        }
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        userDao.save(user);
        return Result.success().message("更新用户信息成功！");
    }
}
