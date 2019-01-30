package cn.edu.nju.software.agile_server.validate;

import cn.edu.nju.software.agile_server.form.ClubCreateForm;
import cn.edu.nju.software.agile_server.form.TourCreateForm;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FormValidate {

    public static Boolean validateTourCreateForm(TourCreateForm form) {
        if (Objects.nonNull(form.getEndTime()) && Objects.nonNull(form.getStartTime())
                && Objects.nonNull(form.getSightId()) && Objects.nonNull(form.getOwnerId())
                && Objects.nonNull(form.getName())) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean validateClubCreateForm(ClubCreateForm form){
        if (Objects.nonNull(form.getName())) {
            return true;
        } else {
            return false;
        }
    }
}
