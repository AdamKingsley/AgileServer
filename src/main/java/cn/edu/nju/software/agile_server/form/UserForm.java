package cn.edu.nju.software.agile_server.form;

import cn.edu.nju.software.agile_server.entity.Sex;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class UserForm {
    private Long id;
    private String openid;
    private Sex sex;
    private String nickname;
    private String avatar;
    private String tel;
    private String province;
    private String city;
    @JsonAlias("province_id")
    private String provinceId;
    @JsonAlias("city_id")
    private String cityId;
    @JsonAlias("area_id")
    private String areaId;
    private String area;
    private String address;
    private String school;
    private String comment;
}
