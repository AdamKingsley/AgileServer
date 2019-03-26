package cn.edu.nju.software.agile_server.entity;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_tour_score")
public class Tour_Score implements Persistable<Long> {

    @Id
    @Column(name = "f_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "f_user_id")
    private Long userId;

    @Column(name = "f_tour_id")
    private Long tourId;

    @Column(name = "score")
    private double score;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
