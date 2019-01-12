package cn.edu.nju.software.agile_server.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {
//    @Id
//    @Column(name = "f_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    @Column(name = "f_sex")
    private Sex sex;

    @Column(name = "f_nickname")
    private String nickname;

    @Column(name = "f_avatar")
    //URL
    private String avatar;

    @Column(name = "f_tel")
    private String tel;

    @Column(name = "f_province")
    private String province;

    @Column(name = "f_city")
    private String city;

    @Column(name = "f_area")
    private String area;

    @Column(name = "f_address")
    private String address;

    @Column(name = "f_school")
    private String school;

    @Column(name = "f_comment", length = 1000)
    private String comment;

    @Column(name = "f_wx_id")
    private String wx_id;
}
