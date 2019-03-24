package cn.edu.nju.software.agile_server.util;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

public class RequestUtil {


    public static Map<String, String> getAttributesStartsWith(String prefix, HttpServletRequest request, boolean remove) {
        Map<String, String> map = Maps.newConcurrentMap();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            if (name.startsWith(prefix)) {
                String data = request.getParameter(name);
                if (remove) {
                    name = name.replace(prefix, "");
                }
                map.put(name, data);
            }
        }
        return map;

    }
}
