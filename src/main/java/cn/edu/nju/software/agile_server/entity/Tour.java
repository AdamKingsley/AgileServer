package cn.edu.nju.software.agile_server.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "t_tour")
public class Tour extends BaseEntity {
    @Column(name = "f_sight_id")
    private Long sightId;

    @Column(name = "f_club_id")
    private Long clubId;

    @Column(name = "f_name")
    private String name;

    @Column(name = "f_startTime")
    private Date startTime;

    @Column(name = "f_endTime")
    private Date endTime;

    @Column(name = "f_nums_of_members")
    private Integer nums;

    @Column(name = "f_top_limit")
    private Integer limit;

    //@Column(name = "f_pics")
    //@ElementCollection
    //private List<String> pics;
    @Column(name = "f_pics",length = 2000)
    private String pics;

    @Column(name = "f_owner_id")
    private Long ownerId;

    @Column(name = "f_description", length = 1000)
    private String description;

}
