package cn.edu.nju.software.agile_server.util;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.List;

public class StringUtil {

    public static List<String> getList(String source,String split){
        return StringUtils.isEmpty(source)? Lists.newArrayList():Lists.newArrayList(source.split(split));
    }
}
