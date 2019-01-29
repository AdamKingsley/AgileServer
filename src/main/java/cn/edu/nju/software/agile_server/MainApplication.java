package cn.edu.nju.software.agile_server;

import cn.edu.nju.software.agile_server.config.WxMessageConverter;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }


    @Bean
    //解决返回plain/text问题，使得json解析器同时支持text格式
    public Decoder feignDecoder() {
        WxMessageConverter wxConverter = new WxMessageConverter();
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(wxConverter);
        return new SpringDecoder(objectFactory);
    }
}

