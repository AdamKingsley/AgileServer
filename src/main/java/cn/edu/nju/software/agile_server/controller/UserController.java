package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.entity.Notification;
import cn.edu.nju.software.agile_server.form.LoginForm;
import cn.edu.nju.software.agile_server.form.NotificationForm;
import cn.edu.nju.software.agile_server.form.UserCommentForm;
import cn.edu.nju.software.agile_server.form.UserForm;
import cn.edu.nju.software.agile_server.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm){
        return userService.login(loginForm);
    }

    @PostMapping("/create")
    public Result create(@RequestBody UserForm userForm){
        return userService.create(userForm);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UserForm userForm){
        return userService.update(userForm);
    }

    @GetMapping("/{userId}")
    public Result getUserById(@PathVariable ("userId")Long userId){
        return userService.getUser(userId);
    }

    @PostMapping("/comment/update")
    public Result updatecomment(@RequestBody UserCommentForm userCommentForm){return userService.updatecomment(userCommentForm);}

    @GetMapping("/notice/{userId}")
    public Result getnotice(@PathVariable ("userId")Long userId){
        return userService.getnotice(userId);
    }

    @PutMapping("/notice/update")
    public Result updatenotice(@RequestBody NotificationForm notificationForm){return userService.updatenotification(notificationForm);}
}
