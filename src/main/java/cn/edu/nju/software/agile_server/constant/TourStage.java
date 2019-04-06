package cn.edu.nju.software.agile_server.constant;

public enum TourStage {
    WAINTING("未开始"),
    RUNNING("进行中"),
    ENDED("已结束");

    private String code;

    TourStage(String code){
        this.code = code;
    }

}
