package cn.edu.nju.software.agile_server.service.impl;

import cn.edu.nju.software.agile_server.common.ResponseCode;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.config.WechatConfig;
import cn.edu.nju.software.agile_server.dao.NotificationRepository;
import cn.edu.nju.software.agile_server.dao.UserRepository;
import cn.edu.nju.software.agile_server.entity.Notification;
import cn.edu.nju.software.agile_server.entity.User;
import cn.edu.nju.software.agile_server.form.LoginForm;
import cn.edu.nju.software.agile_server.form.NotificationForm;
import cn.edu.nju.software.agile_server.form.UserCommentForm;
import cn.edu.nju.software.agile_server.form.UserForm;
import cn.edu.nju.software.agile_server.service.UserService;
import cn.edu.nju.software.agile_server.service.feign.WechatFeign;
import cn.edu.nju.software.agile_server.service.feign.feign_response.LoginFeignResponse;
import cn.edu.nju.software.agile_server.vo.NotificationVO;
import cn.edu.nju.software.agile_server.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private WechatFeign wechatFeign;
    @Resource
    private WechatConfig wechatConfig;
    @Resource
    private UserRepository userDao;
    @Resource
    private NotificationRepository notificationDao;

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

    @Override
    public Result getnotice(Long userid){
        List<NotificationVO> result = new ArrayList<>();
        List<Notification> notificationlist = notificationDao.findAllByUserId(userid);
        for (Notification n:notificationlist) {
            NotificationVO vo = new NotificationVO();
            BeanUtils.copyProperties(n,vo);
            result.add(vo);
        }
        return Result.success().code(200).withData(result);
    }

    @Override
    public Result updatenotification(NotificationForm notificationForm) {
        if (StringUtils.isEmpty(notificationForm.getId())) {
            Result.error().message("更新的通知指定的对应id为空！");
        }
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationForm, notification);
        notificationDao.save(notification);
        return Result.success().message("更新通知信息成功！");
    }

    @Override
    public Result updatecomment(UserCommentForm form) {
        User user = userDao.findUserById(form.getId());
        if(Objects.isNull(user)){
            return Result.error().code(ResponseCode.INVALID_USER).message("要修改的用户不存在！");
        }
        user.setComment(form.getComment());
        userDao.save(user);
        return Result.success().message("更新用户评论成功！");
    }

}
