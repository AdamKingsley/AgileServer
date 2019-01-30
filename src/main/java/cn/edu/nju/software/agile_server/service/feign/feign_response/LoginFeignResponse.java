package cn.edu.nju.software.agile_server.service.feign.feign_response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginFeignResponse {
    //@JsonProperty("access_token")
    //private String accessToken;
    //@JsonProperty("refresh_token")
    //private String refreshToken;
    //@JsonProperty("expires_in")
    //private Long expiresIn;
    @JsonProperty("session_key")
    private String sessionKey;
    private String openid;
    //private String scope;
}
