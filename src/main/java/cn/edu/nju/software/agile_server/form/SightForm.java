package cn.edu.nju.software.agile_server.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "SightForm", description = "景点信息创建的body体")
public class SightForm {
    private String pics;
    private String name;
    private String description;
    private String provinceId;
    private String province;
    private String cityId;
    private String city;
    private String area;
    private String areaId;
    private String address;
    private Double price;
    private String labels;
    private Double score;
    //经度
    private Double lng;
    //维度
    private Double lat;
}
