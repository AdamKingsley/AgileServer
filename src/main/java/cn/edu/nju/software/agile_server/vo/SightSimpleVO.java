package cn.edu.nju.software.agile_server.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class SightSimpleVO {
    @ApiModelProperty(name = "id", value = "景点id", dataType = "Long")
    private Long id;
    @ApiModelProperty(name = "name", value = "景点名称", dataType = "String")
    private String name;
    @ApiModelProperty(name = "pics", value = "景点的展示图片列表URL列表", dataType = "List<String>")
    private List<String> pics;
    @ApiModelProperty(name = "description", value = "景点的介绍", dataType = "String")
    private String description;
    @ApiModelProperty(name = "province", value = "景点位置的省份", dataType = "String")
    private String province;
    @ApiModelProperty(name = "city", value = "景点位置的城市", dataType = "String")
    private String city;
    //    @ApiModelProperty(name = "area", value = "景点位置的分区", dataType = "String")
//    private String area;
//    @ApiModelProperty(name = "address", value = "景点的具体地址", dataType = "String")
//    private String address;
    @ApiModelProperty(name = "price", value = "景点的门票的价格", dataType = "Double")
    private Double price;
    @ApiModelProperty(name = "labels", value = "景点特有的标签列表", dataType = "List<String>")
    private List<String> labels;
    @ApiModelProperty(name = "score", value = "景点的评分")
    private Double score;
}
