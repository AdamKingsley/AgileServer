package cn.edu.nju.software.agile_server.bean;

import lombok.Value;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@Resource
public class RedisClient {

    @Bean
    RedissonClient redissonSingle(
            @Value("${redis.host}") String host,
            @Value("${redis.port}") int port,
            @Value("${redis.password}") String password,
            @Value("${redis.timeout}") int timeout,
            @Value("${redis.pool.min-idle}") int minIdle,
            @Value("${redis.pool.max-total}") int maxTotal
    ){

        Config config = new Config();
        config
                .useSingleServer()
                .setAddress("redis://" + host + ":" + port)
                .setTimeout(timeout)
                .setConnectionPoolSize(maxTotal)
                .setConnectionMinimumIdleSize(minIdle)
                .setPassword(password);
        return Redisson.create(config);
    }
}
