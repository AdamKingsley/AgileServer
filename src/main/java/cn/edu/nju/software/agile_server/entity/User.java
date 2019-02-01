package cn.edu.nju.software.agile_server.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_user")
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity {
    //    @Id
//    @Column(name = "f_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @Column(name = "f_open_id")
    private String openid;

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

    @Column(name = "f_province_id")
    private String provinceId;

    @Column(name = "f_city")
    private String city;

    @Column(name = "f_city_id")
    private String cityId;

    @Column(name = "f_area")
    private String area;

    @Column(name = "f_area_id")
    private String areaId;

    @Column(name = "f_address")
    private String address;

    @Column(name = "f_school")
    private String school;

    @Column(name = "f_comment", length = 1000)
    private String comment;
}
