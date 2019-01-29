package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.User_Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserClubRepository extends JpaRepository<User_Club, Long> {

    List<User_Club> findAllByUserIdAndState(Long userId, Boolean state);

    List<User_Club> findAllByClubIdAndState(Long clubId, Boolean state);

    List<User_Club> findAllByClubIdAndUserIdAndState(Long tourId, Long userId, Boolean state);

}
