package cn.edu.nju.software.agile_server.constant;

public enum TourStage {
    WAINTING(0),
    RUNNING(1),
    ENDED(2);

    private Integer code;

    TourStage(Integer code){
        this.code = code;
    }
}
