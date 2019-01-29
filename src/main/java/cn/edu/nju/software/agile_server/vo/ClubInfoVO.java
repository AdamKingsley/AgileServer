package cn.edu.nju.software.agile_server.vo;

import lombok.Data;

import java.time.Instant;

@Data
public class ClubInfoVO {

    private Long id;

    private String name;

    private String description;

    private Integer top_limit;

    private Boolean joinOrNot;

    private Integer state;

    private Integer nums;

    private Long ownerId;

    private Instant createTime;

    private Instant modifyTime;

    private String address;

    private String area;

    private String city;

    private String province;

//    private Long tourId;

}
