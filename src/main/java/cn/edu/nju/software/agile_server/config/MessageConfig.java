package cn.edu.nju.software.agile_server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "message")
public class MessageConfig {
    private String appKey;
    private String tplId;
}
