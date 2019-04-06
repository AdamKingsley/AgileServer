package cn.edu.nju.software.agile_server.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinTourForm {

    private Long tourId;

    private Long userId;
}
