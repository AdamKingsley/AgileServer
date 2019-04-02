package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.User_Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserClubRepository extends JpaRepository<User_Club, Long> {

    List<User_Club> findAllByUserIdAndState(Long userId, Boolean state);

    List<User_Club> findAllByClubIdAndState(Long clubId, Boolean state);

    @Query(value = "select * from t_user_club as t where t.f_user_id =?1 and t.f_club_id =?2 and t.f_state=?3",nativeQuery = true)
    List<User_Club> findAllByClubIdAndUserIdAndState(Long userId, Long clubId, Boolean state);

}
