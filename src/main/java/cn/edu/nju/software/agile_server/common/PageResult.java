package cn.edu.nju.software.agile_server.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页信息结果类
 */
@Data
public class PageResult {

    @ApiModelProperty(value = "code", name = "状态码", dataType = "String")
    private String code;
    @ApiModelProperty(value = "message", name = "反馈信息", dataType = "String")
    private String message;
    @ApiModelProperty(value = "success", name = "反馈结果", dataType = "Boolean")
    private boolean success;
    @ApiModelProperty(value = "data", name = "反馈数据", dataType = "Object")
    private Object data;
    @ApiModelProperty(value = "pageNum", name = "当前页Number", dataType = "Integer")
    private Integer pageNum;
    @ApiModelProperty(value = "pageSize", name = "每页的size", dataType = "Integer")
    private Integer pageSize;

    public static PageResult success() {
        PageResult PageResult = new PageResult();
        PageResult.success = true;
        PageResult.setCode("200");
        return PageResult;
    }

    public static PageResult error() {
        PageResult PageResult = new PageResult();
        PageResult.success = false;
        return PageResult;
    }

    public PageResult withData(Object data) {
        this.setData(data);
        return this;
    }

    public PageResult code(String code) {
        this.setCode(code);
        return this;
    }

    public PageResult code(int code) {
        this.setCode(code + "");
        return this;
    }

    public PageResult message(String message) {
        this.setMessage(message);
        return this;
    }
}
