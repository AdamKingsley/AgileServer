package cn.edu.nju.software.agile_server.form;

import lombok.Data;

@Data
public class ClubCreateForm {

    //创建不传，修改传
    private Long clubId;

    private Long createTime;

    private Long modifyTime;

    private String description;

    private Long top_limit;

    //private Long nums_of_menmbers;

    private Long owner_id;

    private String pics;
}
