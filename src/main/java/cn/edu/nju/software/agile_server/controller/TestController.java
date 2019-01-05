package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.command.TestCommand;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("hello")
    public String helloWorld() {
        log.info("mapping hello");
        return "Hello World!";
    }

    @PostMapping("insert/test")
    public Result insert(@RequestBody TestCommand command) {
        log.info("mapping insert/test");
        return testService.insert(command);

    }

    @GetMapping("test/{username}")
    public Result insert(@PathVariable String username) {
        log.info("mapping test/{username}");
        return testService.findByUsername(username);
    }
}
