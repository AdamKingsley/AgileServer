package cn.edu.nju.software.agile_server.form;

import lombok.Data;

@Data
public class ClubInviteForm {

    private Long invitedId;

    private Long senderId;

    private Long clubId;
}
