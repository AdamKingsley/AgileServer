package cn.edu.nju.software.agile_server.vo;

import lombok.Data;

@Data
public class TourCommentVO {
    String comment;
    Long userId;
    Long tourId;
    String userName;
    String userAvatar;
}
