package cn.edu.nju.software.agile_server.entity;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_notification")
public class Notification implements Persistable<Long> {

    @Id
    @Column(name = "f_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //"club","tour"
    @Column(name = "f_type")
    private String type;

    @Column(name = "f_user_id")
    private Long use_id;

    @Column(name = "f_time")
    private String time;

    @Column(name = "f_sender_id")
    private Long sender_id;

    @Column(name = "f_club_id")
    private Long club_id;

    @Column(name = "f_tour_id")
    private Long tour_id;

    //state=0:not decided，1:approve，2:deny
    @Column(name = "f_state")
    private Integer state;

    @Override
    public boolean isNew() {
        return null == id;
    }
}
