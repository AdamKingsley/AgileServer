package cn.edu.nju.software.agile_server.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 信息返回类
 */
@Data
@ApiModel(value = "Result", description = "网络请求返回的结构体")
public class Result {

    @ApiModelProperty(value = "code", name = "状态码", dataType = "String")
    private String code;
    @ApiModelProperty(value = "message", name = "反馈信息", dataType = "String")
    private String message;
    @ApiModelProperty(value = "success", name = "反馈结果", dataType = "Boolean")
    private boolean success;
    @ApiModelProperty(value = "data", name = "反馈数据", dataType = "Object")
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.success = true;
        result.setCode("200");
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.success = false;
        return result;
    }

    public Result withData(Object data) {
        this.setData(data);
        return this;
    }

    public Result code(String code) {
        this.setCode(code);
        return this;
    }

    public Result code(int code) {
        this.setCode(code + "");
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }
}
