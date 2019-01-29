package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.form.LoginForm;

public interface UserService {
    Result login(LoginForm loginForm);
}
