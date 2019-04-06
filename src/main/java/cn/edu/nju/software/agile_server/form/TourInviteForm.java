package cn.edu.nju.software.agile_server.form;

import lombok.Data;

@Data
public class TourInviteForm {

    private Long invitedId;

    private Long senderId;

    private Long tourId;
}
