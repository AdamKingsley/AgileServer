package cn.edu.nju.software.agile_server.service.feign;

import cn.edu.nju.software.agile_server.service.feign.feign_response.MessageFeignResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${feign.client.message.value}", url = "${feign.client.message.url}")
public interface MessageFeign {

    @GetMapping("send")
    public MessageFeignResult send(@RequestParam("mobile")String mobile,@RequestParam("tpl_id")String tpl_id,
                                   @RequestParam("tpl_value")String tpl_value,@RequestParam("key")String key);
}
