package cn.edu.nju.software.agile_server.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_area")
public class Area extends AddressEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "f_id")
//    private Long id;

    @Column(name = "f_area")
    private String area;

    @Column(name = "f_area_id")
    private String area_id;

    @Column(name = "f_city_id")
    private String city_id;
}
