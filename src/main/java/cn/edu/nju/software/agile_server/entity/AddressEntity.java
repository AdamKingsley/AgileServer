package cn.edu.nju.software.agile_server.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "f_id")
    private Long id;

}
