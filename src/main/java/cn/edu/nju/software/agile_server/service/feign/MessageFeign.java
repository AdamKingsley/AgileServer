package cn.edu.nju.software.agile_server.service.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "${feign.client.message.value}", url = "${feign.client.message.url}")
public interface MessageFeign {
}
