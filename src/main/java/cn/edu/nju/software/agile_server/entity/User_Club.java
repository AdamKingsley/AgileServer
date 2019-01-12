package cn.edu.nju.software.agile_server.entity;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_user_club")
public class User_Club implements Persistable<Long> {

    @Id
    @Column(name = "f_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "f_state")
    private Boolean state;

    @Column(name = "f_user_id")
    private Long userId;

    @Column(name = "f_club_id")
    private Long clubId;

    @Column(name = "f_joinTime")
    private Date joinTime;

    @Override
    public boolean isNew() {
        return null == id;
    }

    public void setJoinTime(final DateTime joinTime) {

        this.joinTime = null == joinTime ? null : joinTime.toDate();
    }

    public DateTime getJoinTime() {

        return null == joinTime ? null : new DateTime(joinTime);
    }

}
