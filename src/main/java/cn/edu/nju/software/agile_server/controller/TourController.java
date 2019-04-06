package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.form.*;
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

    @GetMapping("/detail/{tourId}/{userId}")
    public Result getTourDetail(@PathVariable("tourId") Long tourId, @PathVariable("userId") Long userId) {
        return tourService.getTourDetail(tourId, userId);
    }

    @PostMapping("/list")
    public Result getTourList(@RequestBody TourListForm tourListForm) {
        return tourService.getTourList(tourListForm);
    }

    @GetMapping("/my/{userId}")
    public Result getMyTourList(@PathVariable("userId") Long userId) {
        return tourService.getMyTourList(userId);
    }

    @GetMapping("/all/{cityId}/{userId}")
    public Result findToursByCityId(@PathVariable("cityId") String cityId, @PathVariable("userId") Long userId) {
        return tourService.findToursByCityId(cityId, userId);
    }

    @PostMapping("/add/score/{tourId}")
    public Result addScore(@PathVariable("tourId") Long tourId, @RequestParam("userId") Long userId, @RequestParam("score") double score) {
        return tourService.addScore(tourId, userId, score);
    }

    @PostMapping("/add/comment/{tourId}")
    public Result addComment(@PathVariable("tourId") Long tourId, @RequestParam("userId") Long userId, @RequestParam("comment") String comment) {
        return tourService.addComment(tourId, userId, comment);
    }

    @GetMapping("/comment/list/{tourId}")
    public Result getTourComment(@PathVariable("tourId") Long tourId) {
        return tourService.getTourComment(tourId);
    }

    @PostMapping("/invite")
    public Result saveInvitationToNotification(@RequestBody TourInviteForm tourInviteForm){
        return tourService.saveInvitationToNotification(tourInviteForm);
    }

    @PostMapping("/reject/{notificationId}")
    public Result reject(@PathVariable("notificationId") Long notificationId) {
        return tourService.reject(notificationId);
    }

    @PostMapping("/agree/{notificationId}")
    public Result agree(@PathVariable("notificationId") Long notificationId) {
        return tourService.agree(notificationId);
    }
}
