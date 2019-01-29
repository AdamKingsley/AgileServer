package cn.edu.nju.software.agile_server.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "地址", description = "可用于省/市/区view层数据的返回")
public class AddressVO {
    @ApiModelProperty(value = "id", name = "唯一标志符", dataType = "String")
    private String id;
    @ApiModelProperty(value = "itemName", name = "具体省/市/区名称")
    private String itemName;
}
