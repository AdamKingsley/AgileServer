package cn.edu.nju.software.agile_server.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "test_table")
public class TestEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name", nullable = false, length = 40)
    private String username;
    @Column(name = "password", nullable = false, length = 50)
    private String password;
}
