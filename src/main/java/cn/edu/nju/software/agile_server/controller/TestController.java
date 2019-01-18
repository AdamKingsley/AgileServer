package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.command.TestCommand;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@Api("TestController")
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("hello")
    @ApiOperation(value = "测试接口1", notes = "返回Hello World！")
    public String helloWorld() {
        log.info("mapping hello");
        return "Hello World!";
    }

    @PostMapping("insert/test")
    @ApiOperation(value = "测试接口2", notes = "返回Hello World！")
    public Result insert(@RequestBody @ApiParam(name = "test commmand", value = "传入的Post请求数据") TestCommand command) {
        log.info("mapping insert/test");
        return testService.insert(command);

    }

    @GetMapping("test/{username}")
    @ApiOperation(value = "测试接口3", notes = "返回Hello World！")
    @ApiImplicitParam(paramType = "path", name = "username", value = "用户名", required = true, dataType = "String")
    public Result insert(@PathVariable String username) {
        log.info("mapping test/{username}");
        return testService.findByUsername(username);
    }
}
