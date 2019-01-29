package cn.edu.nju.software.agile_server.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_sight")
public class Sight extends BaseEntity {
    //@Column(name = "f_pics")
    //@ElementCollection
    //private List<String> pics;
    @Column(name = "f_pics", length = 2000)
    private String pics;

    @Column(name = "f_name")
    private String name;

    @Column(name = "f_description", length = 1000)
    private String description;

    @Column(name = "f_province")
    private String province;

    @Column(name = "f_province_id", length = 10)
    private String provinceId;

    @Column(name = "f_city")
    private String city;

    @Column(name = "f_city_id", length = 10)
    private String cityId;

    @Column(name = "f_area")
    private String area;

    @Column(name = "f_area_id", length = 10)
    private String areaId;

    @Column(name = "f_address")
    private String address;

    @Column(name = "f_price")
    private Double price;

    @Column(name = "f_labels", length = 200)
    private String labels;

    @Column(name = "f_score")
    private Double score;
    //经度
    @Column(name = "f_lng")
    private Double lng;
    //维度
    @Column(name = "f_lat")
    private Double lat;

}
