package cn.edu.nju.software.agile_server.entity;

import cn.edu.nju.software.agile_server.constant.ValidState;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_club")
public class Club extends BaseEntity {
    //@Column(name = "f_pics")
    //@ElementCollection
    //private List<String> pics;
    @Column(name = "f_pics", length = 2000)
    private String pics;

    @Column(name = "f_description", length = 1000)
    private String description;

    @Column(name = "f_name")
    private String name;

    @Column(name = "f_nums_of_members")
    private Integer nums;

    @Column(name = "f_top_limit")
    private Integer limit;

    @Column(name = "f_owner_id")
    private Long ownerId;

    @Column(name = "f_province")
    private String province;

    @Column(name = "f_city")
    private String city;

    @Column(name = "f_area")
    private String area;

    @Column(name = "f_address")
    private String address;

    @Column(name = "f_state")
    private Integer state = ValidState.VALID.ordinal();
}
