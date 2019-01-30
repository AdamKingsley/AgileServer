package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.entity.User;
import cn.edu.nju.software.agile_server.form.LoginForm;
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
}
