package cn.edu.nju.software.agile_server.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "t_sight")
public class Sight extends BaseEntity {
    //@Column(name = "f_pics")
    //@ElementCollection
    //private List<String> pics;
    @Column(name = "f_pics",length = 2000)
    private String pics;

    @Column(name = "f_name")
    private String name;

    @Column(name = "f_description",length = 1000)
    private String description;

    @Column(name = "f_province")
    private String province;

    @Column(name = "f_city")
    private String city;

    @Column(name = "f_area")
    private String area;

    @Column(name = "f_address")
    private String address;

}
