package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.form.LoginForm;
import cn.edu.nju.software.agile_server.form.UserForm;

public interface UserService {
    Result login(LoginForm loginForm);

    Result create(UserForm userForm);

    Result update(UserForm userForm);
}
