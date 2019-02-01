package cn.edu.nju.software.agile_server.service.impl;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.config.MessageConfig;
import cn.edu.nju.software.agile_server.service.MessageService;
import cn.edu.nju.software.agile_server.service.feign.MessageFeign;
import cn.edu.nju.software.agile_server.service.feign.feign_response.MessageFeignResult;
import jodd.util.RandomString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    private static final int VCODE_LENGTH = 6;
    @Resource
    private MessageFeign messageFeign;
    @Resource
    private MessageConfig messageConfig;

    @Override
    public Result sendVcodeMessage(String tel) {
        //随机验证码
        String code = RandomString.getInstance().randomNumeric(VCODE_LENGTH);
        StringBuffer stringBuffer = new StringBuffer().append("#code#=").append(code);
        String tpl_value = stringBuffer.toString();
        MessageFeignResult messageFeignResult = messageFeign.send(tel, messageConfig.getTplId(), tpl_value, messageConfig.getAppKey());
        log.info(messageFeignResult.toString());
        if (messageFeignResult.getErrorCode() == 0) {
            return Result.success().withData(code);
        } else {
            return Result.error().code(messageFeignResult.getErrorCode()).message(messageFeignResult.getReason());
        }
    }
}
