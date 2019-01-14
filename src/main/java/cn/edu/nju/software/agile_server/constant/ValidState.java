package cn.edu.nju.software.agile_server.constant;


public enum ValidState {

    VALID(0),
    INVALID(1);

    private Integer code;

    ValidState(Integer code){
        this.code = code;
    }

}
