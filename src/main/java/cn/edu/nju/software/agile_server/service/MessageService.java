package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.common.Result;

public interface MessageService {

    Result sendVcodeMessage(String tel);
}
