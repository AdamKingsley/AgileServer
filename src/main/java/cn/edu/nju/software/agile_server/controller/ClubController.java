package cn.edu.nju.software.agile_server.controller;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.form.ClubCreateForm;
import cn.edu.nju.software.agile_server.form.ClubInviteForm;
import cn.edu.nju.software.agile_server.form.ClubListForm;
import cn.edu.nju.software.agile_server.form.JoinClubForm;
import cn.edu.nju.software.agile_server.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/club")
public class ClubController {
    @Resource
    private ClubService clubService;

    @PostMapping("/create")
    public Result createClub(@RequestBody ClubCreateForm form) {
        return clubService.createClub(form);
    }

    @PostMapping("/delete")
    public Result deleteClub(@RequestParam("clubId") Long clubId) {
        return clubService.deleteClub(clubId);
    }

    @PostMapping("/update")
    public Result updateClub(@RequestBody ClubCreateForm form) {
        return clubService.updateClub(form);
    }

    @PostMapping("/join")
    public Result joinClub(@RequestBody JoinClubForm form) {
        return clubService.joinClub(form);
    }

    @PostMapping("/exit")
    public Result exitClub(@RequestBody JoinClubForm form) {
        return clubService.exitClub(form);
    }

    @GetMapping("/detail")
    public Result getClubDetail(@RequestParam("clubId") Long clubId, @RequestParam("userId") Long userId) {
        return clubService.getClubDetail(clubId, userId);
    }

    @PostMapping("/list")
    public Result getClubList(@RequestBody ClubListForm clubListForm) {
        return clubService.getClubList(clubListForm);
    }

    @GetMapping("/my")
    public Result getMyClubList(@RequestParam("userId") Long userId) {
        return clubService.getMyClubList(userId);
    }

    @PostMapping("/invite")
    //把Param都改成body（因为前端data是一个参数）
    public Result saveInvitationToNotification(@RequestBody ClubInviteForm clubInviteForm){
        return clubService.saveInvitationToNotification(clubInviteForm);
    }
}

