package cn.edu.nju.software.agile_server.form;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TourCreateForm {
    /**
     * 景点ID
     */
    private Long sightId;

    /**
     * 社团ID，公开的不需要传
     */
    private Long clubId;

    private String name;

    private Timestamp startTime;

    private Timestamp endTime;

    private Integer limit;

    private String pics;

    /**
     * 创建者userId
     */
    private Long ownerId;

    private String description;

    /**
     * 创建时不传，修改时传
     */
    private Long tourId;
}
