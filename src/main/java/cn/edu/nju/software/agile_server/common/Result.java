package cn.edu.nju.software.agile_server.common;

import lombok.Data;

/**
 * 信息返回类
 */
@Data
public class Result {

    private String code;
    private String message;
    private boolean success;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.success = true;
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
