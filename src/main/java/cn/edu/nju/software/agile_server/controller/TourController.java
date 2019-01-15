package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.form.JoinTourForm;
import cn.edu.nju.software.agile_server.form.TourCreateForm;
import cn.edu.nju.software.agile_server.form.TourListForm;
import cn.edu.nju.software.agile_server.service.TourService;
import cn.edu.nju.software.agile_server.vo.TourInfoVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/tour")
public class TourController {
    @Resource
    private TourService tourService;

    @PostMapping("/create")
    public Result createTour(@RequestBody TourCreateForm form) {
        return tourService.createTour(form);
    }

    @PostMapping("/delete")
    public Result deleteTour(@RequestParam("tourId") Long tourId) {
        return tourService.deleteTour(tourId);
    }

    @PostMapping("/update")
    public Result updateTour(@RequestBody TourCreateForm form) {
        return tourService.updateTour(form);
    }

    @PostMapping("/join")
    public Result joinTour(@RequestBody JoinTourForm form) {
        return tourService.joinTour(form);
    }

    @PostMapping("/exit")
    public Result exitTour(@RequestBody JoinTourForm form) {
        return tourService.exitTour(form);
    }

    @GetMapping("/detail")
    public Result getTourDetail(@RequestParam("tourId") Long tourId, @RequestParam("userId") Long userId) {
        return tourService.getTourDetail(tourId, userId);
    }

    @PostMapping("/list")
    public Result getTourList(@RequestBody TourListForm tourListForm) {
        return tourService.getTourList(tourListForm);
    }
}
