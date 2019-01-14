package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.form.TourCreateForm;

public interface TourService {

    Result createTour(TourCreateForm form);

    Result deleteTour(Long tourId);

    Result updateTour(TourCreateForm form);

}
