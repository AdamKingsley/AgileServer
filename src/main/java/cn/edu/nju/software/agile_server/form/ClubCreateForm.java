package cn.edu.nju.software.agile_server.form;

import lombok.Data;

import java.time.Instant;

@Data
public class ClubCreateForm {

    //创建不传，修改传
    private Long clubId;

    private String name;

    private Instant createTime;

    private Instant modifyTime;

    private String description;

    private Integer top_limit;

    //private Long nums_of_menmbers;

    private Long owner_id;

    private String pics;
}
