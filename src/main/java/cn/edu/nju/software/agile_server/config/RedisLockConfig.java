package cn.edu.nju.software.agile_server.config;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisLockConfig {

    @Autowired
    RedissonClient redissonClient;


    @Bean
    public RedisLockUtilImpl distributedLocker(RedissonClient redissonClient){
        return new RedisLockUtilImpl(redissonClient);
    }

}
