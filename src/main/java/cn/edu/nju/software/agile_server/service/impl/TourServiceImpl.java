package cn.edu.nju.software.agile_server.service.impl;

import cn.edu.nju.software.agile_server.common.ResponseCode;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.constant.TourStage;
import cn.edu.nju.software.agile_server.constant.ValidState;
import cn.edu.nju.software.agile_server.dao.*;
import cn.edu.nju.software.agile_server.entity.*;
import cn.edu.nju.software.agile_server.form.JoinTourForm;
import cn.edu.nju.software.agile_server.form.TourCreateForm;
import cn.edu.nju.software.agile_server.form.TourListForm;
import cn.edu.nju.software.agile_server.service.TourService;
import cn.edu.nju.software.agile_server.validate.FormValidate;
import cn.edu.nju.software.agile_server.vo.TourInfoVO;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class TourServiceImpl implements TourService {

    @Resource
    private TourRepository tourDao;
    @Resource
    private UserTourRepository userTourDao;
    @Resource
    private UserRepository userDao;
    @Resource
    private UserClubRepository userClubDao;
    @Resource
    private SightRepository sightDao;

    @Override
    public Result createTour(TourCreateForm form) {
        long now = System.currentTimeMillis();
        if (!FormValidate.validateTourCreateForm(form)) {
            return Result.error().code(ResponseCode.EMPTY_FORM).message("表单信息不完整，请补全！");
        }
        if (form.getStartTime() > form.getEndTime()
                || form.getStartTime() > now
                || form.getEndTime() < now) {

            return Result.error().code(ResponseCode.WRONG_TIME).message("请填写正确的起始时间！");
        }
        Tour tour = new Tour();
        tour.setName(form.getName());
        tour.setOwnerId(form.getOwnerId());
        tour.setStartTime(Instant.ofEpochMilli(form.getStartTime()));
        tour.setEndTime(Instant.ofEpochMilli(form.getEndTime()));
        tour.setSightId(form.getSightId());
        tour.setDescription(form.getDescription());
        tour.setClubId(form.getClubId());
        tour.setPics(form.getPics());
        tour.setLimit(form.getLimit());
        try {
            Tour tourEntity = tourDao.save(tour);

            TourInfoVO result = new TourInfoVO();
            BeanUtils.copyProperties(tourEntity, result);
            result.setJoinOrNot(true);
            result.setStage(checkTourStage(form.getStartTime(), form.getEndTime()));

            return Result.success().message("创建出游成功！").withData(result);
        } catch (Exception e) {
            return Result.error().message("创建出游失败，数据库更新错误！").code(ResponseCode.DB_UPDATE_ERROR);
        }
    }

    @Override
    public Result deleteTour(Long tourId) {
        Tour tour = tourDao.findByIdAndState(tourId, ValidState.VALID.ordinal());
        if (Objects.isNull(tour)) {
            return Result.error().code(ResponseCode.INVALID_TOUR).message("要删除的出游不存在！");
        }
        //先删除user_tour关系表
        List<User_Tour> userTourList = userTourDao.findAllByTourIdAndState(tourId, true);
        userTourList.forEach(u -> u.setState(Boolean.FALSE));
        try {
            userTourDao.saveAll(userTourList);
        } catch (Exception e) {
            return Result.error().code(ResponseCode.DB_DELETE_ERROR).message("删除user_tour表出错！");
        }

        try {
            tour.setState(ValidState.INVALID.ordinal());
            tourDao.saveAndFlush(tour);
        } catch (Exception e) {
            return Result.error().code(ResponseCode.DB_DELETE_ERROR).message("删除tour表出错！");
        }
        return Result.success().code(200).message("删除出游成功！");
    }

    @Override
    public Result updateTour(TourCreateForm form) {
        Tour tour = tourDao.findByIdAndState(form.getTourId(), 0);
        if (Objects.isNull(tour)) {
            return Result.error().code(ResponseCode.INVALID_TOUR).message("要修改的出游不存在！");
        }
        if (checkTourStage(form.getStartTime(), form.getEndTime()) == TourStage.ENDED.ordinal()) {
            return Result.error().code(ResponseCode.UPDATE_ENDED_TOUR).message("已经结束的出游无法修改！");
        }
        tour.setName(form.getName());
        tour.setOwnerId(form.getOwnerId());
        tour.setStartTime(Instant.ofEpochMilli(form.getStartTime()));
        tour.setEndTime(Instant.ofEpochMilli(form.getEndTime()));
        tour.setSightId(form.getSightId());
        tour.setDescription(form.getDescription());
        tour.setClubId(form.getClubId());
        tour.setPics(form.getPics());
        tour.setLimit(form.getLimit());

        Tour tourEntity = tourDao.saveAndFlush(tour);
        TourInfoVO result = new TourInfoVO();
        BeanUtils.copyProperties(tourEntity, result);
        result.setJoinOrNot(true);
        result.setStage(checkTourStage(form.getStartTime(), form.getEndTime()));

        return Result.success().message("更新出游成功！").withData(result);
    }

    @Override
    public Result joinTour(JoinTourForm form) {
        Instant now = Instant.ofEpochMilli(System.currentTimeMillis());
        Tour tour  = tourDao.findByIdAndState(form.getTourId(), 0);
        if (Objects.isNull(tour)) {
            return Result.error().code(ResponseCode.INVALID_TOUR).message("要加入的出游不存在！");
        }
        User user = userDao.findUserById(form.getUserId());
        if (Objects.isNull(user)) {
            return Result.error().code(ResponseCode.INVALID_USER).message("用户不存在！");
        }

        if (Objects.nonNull(tour.getLimit()) &&
                tour.getNums() >= tour.getLimit()) {
            return Result.error().code(ResponseCode.ENOUGH_NUM_TOUR).message("当前出游已达到人数上限，无法加入！");
        }
        if (tour.getEndTime().compareTo(now) < 0) {
            return Result.error().code(ResponseCode.UPDATE_ENDED_TOUR).message("无法加入已经结束的出游！");
        }

        User_Tour userTour = new User_Tour();
        userTour.setState(true);
        userTour.setTourId(form.getTourId());
        userTour.setUserId(form.getUserId());
        DateTime time= new DateTime();
        userTour.setJoinTime(time);
        userTourDao.saveAndFlush(userTour);

        //todo redis分布式锁
        tour.setNums(tour.getNums() + 1);
        tourDao.saveAndFlush(tour);
        return Result.success().code(200).message("成功加入出游！");
    }

    @Override
    public Result exitTour(JoinTourForm form) {
        Instant now = Instant.ofEpochMilli(System.currentTimeMillis());
        Tour tour  = tourDao.findByIdAndState(form.getTourId(), 0);
        if (Objects.isNull(tour)) {
            return Result.error().code(ResponseCode.INVALID_TOUR).message("要退出的出游不存在！");
        }
        User user = userDao.findUserById(form.getUserId());
        if (Objects.isNull(user)) {

            return Result.error().code(ResponseCode.INVALID_USER).message("用户不存在！");
        }

        if (tour.getEndTime().compareTo(now) < 0) {
            return Result.error().code(ResponseCode.UPDATE_ENDED_TOUR).message("无法退出已经结束的出游！");
        }
        List<User_Tour> userTourList = userTourDao.findAllByTourIdAndUserIdAndState(form.getTourId(),
                form.getUserId(), true);

        if (userTourList.isEmpty()) {
            return Result.error().code(ResponseCode.NOT_JOINED_TOUR).message("无法退出未加入的出游！");
        }

        User_Tour userTour = userTourList.get(0);
        userTour.setState(false);
        userTourDao.saveAndFlush(userTour);

        //todo 分布式锁
        tour.setNums(tour.getNums() - 1);
        tourDao.saveAndFlush(tour);
        return Result.success().code(200).message("成功退出出游！");
    }

    @Override
    public Result getTourDetail(Long tourId, Long userId) {
        Tour tour = tourDao.findByIdAndState(tourId, ValidState.VALID.ordinal());
        if (Objects.isNull(tour)) {
            return Result.error().code(ResponseCode.INVALID_TOUR).message("要查看的出游不存在!");
        }
        TourInfoVO result = new TourInfoVO();
        BeanUtils.copyProperties(tour, result);
        result.setStage(checkTourStage(tour.getStartTime().toEpochMilli(), tour.getEndTime().toEpochMilli()));

        List<User_Tour> userTour = userTourDao.findAllByTourIdAndUserIdAndState(tourId, userId, true);

        if (userTour.isEmpty()) {
            result.setJoinOrNot(false);
        } else {
            result.setJoinOrNot(true);
        }
        return Result.success().code(200).withData(result);
    }

    @Override
    public Result getTourList(TourListForm form) {
        List<Tour> publicTour = tourDao.findByState(ValidState.VALID.ordinal()).stream().filter(t -> t.getClubId() == null).collect(
            Collectors.toList());
        List<Long> clubIds = userClubDao.findAllByUserIdAndState(form.getUserId(), true).stream()
                .map(User_Club::getClubId).collect(Collectors.toList());
        List<Tour> clubTour = tourDao.findAllByClubIdExistsAndState(clubIds, ValidState.VALID.ordinal());
        List<Tour> totalTour = new ArrayList<>(publicTour);
        totalTour.addAll(clubTour);

        if (Objects.nonNull(form.getStage())) {
            totalTour = totalTour.stream().filter(t -> t.getState().equals(form.getStage())).collect(Collectors.toList());
        }
        if (Objects.nonNull(form.getSightId())) {
            totalTour = totalTour.stream().filter(t -> t.getSightId().equals(form.getSightId())).collect(Collectors.toList());
        }
        if (Objects.nonNull(form.getClubId())) {
            totalTour = totalTour.stream().filter(t -> t.getClubId()!= null && t.getClubId().equals(form.getClubId()))
                    .collect(Collectors.toList());
        }
        if (Objects.nonNull(form.getStartTime())) {
            totalTour = totalTour.stream().filter(t -> t.getStartTime().compareTo(form.getStartTime()) > 0)
                    .collect(Collectors.toList());
        }
        if (Objects.nonNull(form.getEndTime())) {
            totalTour = totalTour.stream().filter(t -> t.getEndTime().compareTo(form.getEndTime()) < 0)
                    .collect(Collectors.toList());
        }
        if (Objects.nonNull(form.getCity())) {
            List<Long> sightIds = totalTour.stream().map(Tour::getSightId).collect(Collectors.toList());
            List<Long> matchSights = sightDao.findAllById(sightIds).stream().filter(s -> s.getCity().equals(form.getCity()))
                    .map(Sight::getId).collect(Collectors.toList());
            totalTour = totalTour.stream().filter(t -> matchSights.contains(t.getSightId())).collect(Collectors.toList());
        }

        if (Objects.nonNull(form.getName())) {
            //TODO
        }

        /**
         * 默认按照未开始、进行中、已经结束排序
         */
        totalTour = sortByStage(totalTour);
        List<TourInfoVO> result = new ArrayList<>();
        List<Long> joinTourIds = userTourDao.findAllByUserIdAndState(form.getUserId(), true)
                .stream().map(User_Tour::getTourId).collect(Collectors.toList());
        for (Tour t : totalTour) {
            TourInfoVO vo = new TourInfoVO();
            BeanUtils.copyProperties(t, vo);
            vo.setStage(checkTourStage(t.getStartTime().toEpochMilli(), t.getEndTime().toEpochMilli()));
            if (joinTourIds.contains(t.getId())) {
                vo.setJoinOrNot(true);
            } else {
                vo.setJoinOrNot(false);
            }
            result.add(vo);
        }

        return Result.success().code(200).withData(result);
    }

    @Override
    public Result getMyTourList(Long userId) {
        List<Long> tourIds = userTourDao.findAllByUserIdAndState(userId, true)
                .stream().map(User_Tour::getTourId).collect(Collectors.toList());

        List<Tour> tourList = tourDao.findAllById(tourIds).stream().filter(t -> t.getState() == ValidState.VALID.ordinal())
                .collect(Collectors.toList());
        List<Tour> sorted = sortByStage(tourList);

        List<TourInfoVO> result = new ArrayList<>();
        for (Tour t : sorted) {
            TourInfoVO vo = new TourInfoVO();
            BeanUtils.copyProperties(t, vo);
            vo.setStage(checkTourStage(t.getStartTime().toEpochMilli(), t.getEndTime().toEpochMilli()));
            vo.setJoinOrNot(true);
            result.add(vo);
        }
        return Result.success().code(200).withData(result);
    }

    private int checkTourStage(Long startTime, Long endTime) {
        Long now = System.currentTimeMillis();
        if (startTime > now) {
            return TourStage.WAINTING.ordinal();
        } else if (startTime < now && now > endTime) {
            return TourStage.RUNNING.ordinal();
        } else if (endTime < now) {
            return TourStage.ENDED.ordinal();
        }
        return 0;
    }

    private List<Tour> sortByStage(List<Tour> tourList) {
        List<Tour> notStarted = tourList.stream().filter(t ->
                checkTourStage(t.getStartTime().toEpochMilli(), t.getEndTime().toEpochMilli()) == TourStage.WAINTING.ordinal())
                .collect(Collectors.toList());
        List<Tour> running = tourList.stream().filter(t ->
                checkTourStage(t.getStartTime().toEpochMilli(), t.getEndTime().toEpochMilli()) == TourStage.RUNNING.ordinal())
                .collect(Collectors.toList());
        List<Tour> ended = tourList.stream().filter(t ->
                checkTourStage(t.getStartTime().toEpochMilli(), t.getEndTime().toEpochMilli()) == TourStage.ENDED.ordinal())
                .collect(Collectors.toList());
        List<Tour> result = new ArrayList<>(notStarted);

        result.addAll(running);
        result.addAll(ended);
        return result;

    }
}
