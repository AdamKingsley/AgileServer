package cn.edu.nju.software.agile_server.vo;


import lombok.Data;

import java.time.Instant;

@Data
public class TourInfoVO {

    private Long sightId;

    private Long clubId;

    private String name;

    private Instant startTime;

    private Instant endTime;

    private Integer nums;

    private Integer limit;

    private String pics;

    private Long ownerId;

    private String description;

    private Integer state;

    private Boolean joinOrNot;

    private Integer stage;

    private Double point;
}
