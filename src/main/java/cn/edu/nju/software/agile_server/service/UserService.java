package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.entity.Notification;
import cn.edu.nju.software.agile_server.form.LoginForm;
import cn.edu.nju.software.agile_server.form.NotificationForm;
import cn.edu.nju.software.agile_server.form.UserCommentForm;
import cn.edu.nju.software.agile_server.form.UserForm;

public interface UserService {
    Result login(LoginForm loginForm);

    Result create(UserForm userForm);

    Result update(UserForm userForm);

    Result getUser(Long userid);

    Result getnotice(Long userid);

    Result updatenotification(NotificationForm notificationForm);

    Result updatecomment(UserCommentForm userCommentForm);
}
