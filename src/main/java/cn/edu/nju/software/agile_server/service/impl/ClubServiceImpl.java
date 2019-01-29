package cn.edu.nju.software.agile_server.service.impl;

import cn.edu.nju.software.agile_server.common.ResponseCode;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.constant.ValidState;
import cn.edu.nju.software.agile_server.dao.ClubRepository;
import cn.edu.nju.software.agile_server.dao.UserClubRepository;
import cn.edu.nju.software.agile_server.dao.UserRepository;
import cn.edu.nju.software.agile_server.entity.Club;
import cn.edu.nju.software.agile_server.entity.User;
import cn.edu.nju.software.agile_server.entity.User_Club;
import cn.edu.nju.software.agile_server.entity.User_Tour;
import cn.edu.nju.software.agile_server.form.ClubCreateForm;
import cn.edu.nju.software.agile_server.form.ClubListForm;
import cn.edu.nju.software.agile_server.form.JoinClubForm;
import cn.edu.nju.software.agile_server.service.ClubService;
import cn.edu.nju.software.agile_server.validate.FormValidate;
import cn.edu.nju.software.agile_server.vo.ClubInfoVO;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClubServiceImpl implements ClubService {

    @Resource
    private ClubRepository clubDao;
    @Resource
    private UserClubRepository userClubDao;
    @Resource
    private UserRepository userDao;

    @Override
    public Result createClub(ClubCreateForm form) {
        long now = System.currentTimeMillis();
        if (!FormValidate.validateClubCreateForm(form)) {
            return Result.error().code(ResponseCode.EMPTY_FORM).message("表单信息不完整，请补全！");
        }

        Club club = new Club();
        club.setName(form.getName());
        club.setDescription(form.getDescription());
        club.setLimit(form.getTop_limit());
        club.setPics(form.getPics());
        try {
            Club tourEntity = clubDao.save(club);

            ClubInfoVO result = new ClubInfoVO();
            BeanUtils.copyProperties(tourEntity, result);
            result.setJoinOrNot(true);
            return Result.success().message("创建社团成功！").withData(result);
        }catch (Exception e) {
            return Result.error().message("创建社团失败，数据库更新错误！").code(ResponseCode.DB_UPDATE_ERROR);
        }
    }

    @Override
    public Result deleteClub(Long clubId) {
        Club club = clubDao.findByIdAndState(clubId, ValidState.VALID.ordinal());
        if (Objects.isNull(club)) {
            return Result.error().code(ResponseCode.INVALID_TOUR).message("要删除的社团不存在！");
        }
        //先删除user_club关系表
        List<User_Club> userClubList = userClubDao.findAllByClubIdAndState(clubId, true);
        userClubList.forEach(u -> u.setState(Boolean.FALSE));
        try {
            userClubDao.saveAll(userClubList);
        } catch (Exception e) {
            return Result.error().code(ResponseCode.DB_DELETE_ERROR).message("删除user_club表出错！");
        }

        try {
            club.setState(ValidState.INVALID.ordinal());
            clubDao.saveAndFlush(club);
        } catch (Exception e) {
            return Result.error().code(ResponseCode.DB_DELETE_ERROR).message("删除club表出错！");
        }
        return Result.success().code(200).message("删除社团成功！");
    }

    @Override
    public Result updateClub(ClubCreateForm form) {
        Club club = clubDao.findByIdAndState(form.getClubId(), 0);
        if (Objects.isNull(club)) {
            return Result.error().code(ResponseCode.INVALID_TOUR).message("要修改的社团不存在！");
        }
        club.setName(form.getName());
        club.setDescription(form.getDescription());
        club.setLimit(form.getTop_limit());
        club.setPics(form.getPics());

        Club clubEntity = clubDao.saveAndFlush(club);
        ClubInfoVO result = new ClubInfoVO();
        BeanUtils.copyProperties(clubEntity, result);
        result.setJoinOrNot(true);

        return Result.success().message("更新社团成功！").withData(result);
    }

    @Override
    public Result joinClub(JoinClubForm form) {
        Instant now = Instant.ofEpochMilli(System.currentTimeMillis());
        Club club = clubDao.findByIdAndState(form.getClubId(), 0);
        if (Objects.isNull(club)) {
            return Result.error().code(ResponseCode.INVALID_TOUR).message("要加入的社团不存在！");
        }
        User user = userDao.findUserById(form.getUserId());
        if (Objects.isNull(user)) {
            return Result.error().code(ResponseCode.INVALID_USER).message("用户不存在！");
        }

        if (Objects.nonNull(club.getLimit()) &&
                club.getNums() >= club.getLimit()) {
            return Result.error().code(ResponseCode.ENOUGH_NUM_TOUR).message("当前社团已达到人数上限，无法加入！");
        }


        User_Club userClub = new User_Club();
        userClub.setState(true);
        userClub.setClubId(form.getClubId());
        userClub.setUserId(form.getUserId());
        DateTime time = new DateTime();
        userClub.setJoinTime(time);
        userClubDao.saveAndFlush(userClub);

        //todo redis分布式锁
        club.setNums(club.getNums() + 1);
        clubDao.saveAndFlush(club);
        return Result.success().code(200).message("成功加入社团！");
    }

    @Override
    public Result exitClub(JoinClubForm form) {
        Instant now = Instant.ofEpochMilli(System.currentTimeMillis());
        Club club = clubDao.findByIdAndState(form.getClubId(), 0);
        if (Objects.isNull(club)) {
            return Result.error().code(ResponseCode.INVALID_TOUR).message("要退出的社团不存在！");
        }
        User user = userDao.findUserById(form.getUserId());
        if (Objects.isNull(user)) {

            return Result.error().code(ResponseCode.INVALID_USER).message("用户不存在！");
        }

        List<User_Club> userClubList = userClubDao.findAllByClubIdAndUserIdAndState(form.getClubId(),
                form.getUserId(), true);

        if (userClubList.isEmpty()) {
            return Result.error().code(ResponseCode.NOT_JOINED_TOUR).message("无法退出未加入的社团！");
        }

        User_Club userClub = userClubList.get(0);
        userClub.setState(false);
        userClubDao.saveAndFlush(userClub);

        //todo 分布式锁
        club.setNums(club.getNums() - 1);
        clubDao.saveAndFlush(club);
        return Result.success().code(200).message("成功退出社团！");
    }

    @Override
    public Result getClubDetail(Long clubId, Long userId) {
        Club club = clubDao.findByIdAndState(clubId, ValidState.VALID.ordinal());
        if (Objects.isNull(club)) {
            return Result.error().code(ResponseCode.INVALID_TOUR).message("要查看的社团不存在!");
        }
        ClubInfoVO result = new ClubInfoVO();
        BeanUtils.copyProperties(club, result);

        List<User_Club> userClub = userClubDao.findAllByClubIdAndUserIdAndState(clubId, userId, true);

        if (userClub.isEmpty()) {
            result.setJoinOrNot(false);
        } else {
            result.setJoinOrNot(true);
        }
        return Result.success().code(200).withData(result);
    }

    @Override
    public Result getClubList(ClubListForm form) {
        List<Club> totalClub = clubDao.findByState(ValidState.VALID.ordinal());



        /**
         * 默认按照创建时间排序
         */
        totalClub = sortByCreateTime(totalClub);
        List<ClubInfoVO> result = new ArrayList<>();
        List<Long> joinClubIds = userClubDao.findAllByUserIdAndState(form.getUserId(), true)
                .stream().map(User_Club::getClubId).collect(Collectors.toList());
        for (Club c : totalClub) {
            ClubInfoVO vo = new ClubInfoVO();
            BeanUtils.copyProperties(c, vo);
            if (joinClubIds.contains(c.getId())) {
                vo.setJoinOrNot(true);
            } else {
                vo.setJoinOrNot(false);
            }
            result.add(vo);
        }

        return Result.success().code(200).withData(result);
    }


    @Override
    public Result getMyClubList(Long userId) {
        return null;
    }

    private List<Club> sortByCreateTime(List<Club> totalClub) {
        return null;
    }
}
