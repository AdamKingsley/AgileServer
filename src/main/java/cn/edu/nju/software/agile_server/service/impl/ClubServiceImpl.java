package cn.edu.nju.software.agile_server.service.impl;

import cn.edu.nju.software.agile_server.common.ResponseCode;
import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.constant.ValidState;
import cn.edu.nju.software.agile_server.dao.ClubRepository;
import cn.edu.nju.software.agile_server.dao.UserClubRepository;
import cn.edu.nju.software.agile_server.entity.Club;
import cn.edu.nju.software.agile_server.entity.User_Club;
import cn.edu.nju.software.agile_server.form.ClubCreateForm;
import cn.edu.nju.software.agile_server.form.ClubListForm;
import cn.edu.nju.software.agile_server.form.JoinClubForm;
import cn.edu.nju.software.agile_server.service.ClubService;
import cn.edu.nju.software.agile_server.validate.FormValidate;
import cn.edu.nju.software.agile_server.vo.ClubInfoVO;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class ClubServiceImpl implements ClubService {

    @Resource
    private ClubRepository clubDao;
    @Resource
    private UserClubRepository userClubDao;

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
        return null;
    }

    @Override
    public Result joinClub(JoinClubForm form) {
        return null;
    }

    @Override
    public Result exitClub(JoinClubForm form) {
        return null;
    }

    @Override
    public Result getClubDetail(Long clubId, Long userId) {
        return null;
    }

    @Override
    public Result getClubList(ClubListForm form) {
        return null;
    }

    @Override
    public Result getMyClubList(Long userId) {
        return null;
    }
}
