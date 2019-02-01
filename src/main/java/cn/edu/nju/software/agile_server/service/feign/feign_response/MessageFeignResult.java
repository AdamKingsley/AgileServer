package cn.edu.nju.software.agile_server.service.feign.feign_response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
//@ToString
public class MessageFeignResult {
    private String reason;
    //private String result;
    @JsonAlias("error_code")
    private Integer errorCode;
}
