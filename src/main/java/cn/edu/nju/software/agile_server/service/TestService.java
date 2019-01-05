package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.command.TestCommand;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.dto.TestDto;

public interface TestService {

    public Result insert(TestCommand command);

    public Result findByUsername(String username);
}
