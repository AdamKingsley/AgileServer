package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.User_Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTourRepository extends JpaRepository<User_Tour, Long> {

    List<User_Tour> findAllByTourIdAndState(Long tourId, Boolean state);

    List<User_Tour> findAllByTourIdAndUserIdAndState(Long tourId, Long userId, Boolean state);

    List<User_Tour> findAllByUserIdAndState(Long userId, Boolean state);
}
