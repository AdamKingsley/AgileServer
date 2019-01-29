package cn.edu.nju.software.agile_server.form;

import lombok.Data;
import java.time.Instant;


/**
 * @author dd
 *
 */
@Data
public class ClubListForm {

    private String name;

    private Instant createTime;

    private Long num_of_members;

    private Long top_limit;

    private String description;

    private String pics;

    private Long userId;
}
