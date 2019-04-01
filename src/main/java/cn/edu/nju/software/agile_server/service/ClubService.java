package cn.edu.nju.software.agile_server.service;

import cn.edu.nju.software.agile_server.common.Result;
import cn.edu.nju.software.agile_server.form.ClubCreateForm;
import cn.edu.nju.software.agile_server.form.ClubListForm;
import cn.edu.nju.software.agile_server.form.JoinClubForm;

public interface ClubService {
    Result createClub(ClubCreateForm form);

    Result deleteClub(Long clubId);

    Result updateClub(ClubCreateForm form);

    Result joinClub(JoinClubForm form);

    Result exitClub(JoinClubForm form);

    Result getClubDetail(Long clubId, Long userId);

    Result getClubList(ClubListForm form);

    Result getMyClubList(Long userId);

    Result saveInvitationToNotification(Long userId,Long senderId,Long clubId);
}
