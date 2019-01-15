package cn.edu.nju.software.agile_server.form;

import java.time.Instant;
import lombok.Data;

/**
 * @author zsq
 * @version 1.0
 * @date 2019/01/15
 */
@Data
public class TourListForm {

  private Long sightId;

  private Instant startTime;

  private Instant endTime;

  private Integer state;

  private Long clubId;

  private String city;

  private Long userId;

  private String name;


}
