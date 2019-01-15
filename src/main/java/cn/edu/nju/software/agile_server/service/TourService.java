package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.form.JoinTourForm;
import cn.edu.nju.software.agile_server.form.TourCreateForm;
import cn.edu.nju.software.agile_server.form.TourListForm;

public interface TourService {

    Result createTour(TourCreateForm form);

    Result deleteTour(Long tourId);

    Result updateTour(TourCreateForm form);

    Result joinTour(JoinTourForm form);

    Result exitTour(JoinTourForm form);

    Result getTourDetail(Long tourId, Long userId);

    Result getTourList(TourListForm form);

    Result getMyTourList(Long userId);


}
