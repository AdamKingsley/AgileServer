package cn.edu.nju.software.agile_server.form;

import lombok.Data;

@Data
public class NotificationForm {
    private Long id;
    private String type;

    private Long user_id;

    private String time;

    private Long sender_id;

    private Long club_id;

    private Long tour_id;

    private Integer state;
}
