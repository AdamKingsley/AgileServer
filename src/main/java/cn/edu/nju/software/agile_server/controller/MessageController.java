package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("message")
public class MessageController {
    @Resource
    private MessageService messageService;

    @GetMapping("send")
    public Result sendMessage(@RequestParam("tel") String tel) {
        return messageService.sendVcodeMessage(tel);
    }
}
