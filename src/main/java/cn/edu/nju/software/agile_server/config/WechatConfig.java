package cn.edu.nju.software.agile_server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WechatConfig {
    private String appid;
    private String secret;
    private String grantType;
}
