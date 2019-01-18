package cn.edu.nju.software.agile_server.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "test command对象")
public class TestCommand {
    @ApiModelProperty(name = "用户名", value = "字符串", required = true, dataType = "String")
    private String username;
    @ApiModelProperty(name = "密码", value = "字符串", required = true, dataType = "String")
    private String password;
}
