package cn.edu.nju.software.agile_server.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_city")
public class City extends AddressEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "f_id")
//    private Long id;

    @Column(name = "f_city")
    private String city;

    @Column(name = "f_city_id")
    private String city_id;

    @Column(name = "f_province_id")
    private String province_id;
}
