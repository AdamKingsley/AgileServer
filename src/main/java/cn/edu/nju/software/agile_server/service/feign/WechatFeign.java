package cn.edu.nju.software.agile_server.service.feign;

import cn.edu.nju.software.agile_server.service.feign.feign_response.LoginFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${feign.client.wechat.value}", url = "${feign.client.wechat.url}")
public interface WechatFeign {

    @GetMapping("jscode2session")
    public LoginFeignResponse login(@RequestParam("appid") String appid, @RequestParam("secret") String secret,
                                    @RequestParam("js_code") String code, @RequestParam("grant_type") String grantType);

}
