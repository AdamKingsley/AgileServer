package cn.edu.nju.software.agile_server.vo;

import cn.edu.nju.software.agile_server.entity.Sex;
import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String openid;
    private Sex sex;
    private String nickname;
    private String avatar;
    private String tel;
    private String province;
    private String city;
    private String provinceId;
    private String cityId;
    private String areaId;
    private String area;
    private String address;
    private String school;
    //private String comment;
}
