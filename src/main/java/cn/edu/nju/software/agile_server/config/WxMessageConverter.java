package cn.edu.nju.software.agile_server.config;

import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

// 创建一个新的转换器 解析微信的 [text/plain]
public class WxMessageConverter extends MappingJackson2HttpMessageConverter {
    public WxMessageConverter(){
        List<MediaType> mediaTypes = Lists.newArrayList();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(mediaTypes);
    }
}