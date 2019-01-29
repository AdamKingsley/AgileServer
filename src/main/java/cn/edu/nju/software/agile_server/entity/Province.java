package cn.edu.nju.software.agile_server.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_province")
public class Province extends AddressEntity {

    @Column(name = "f_province")
    private String province;

    @Column(name = "f_province_id")
    private String province_id;
}
