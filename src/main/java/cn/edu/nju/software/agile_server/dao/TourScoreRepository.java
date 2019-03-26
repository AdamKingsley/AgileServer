package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.Tour_Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourScoreRepository extends JpaRepository<Tour_Score, Long> {

    List<Tour_Score> findAllByTourIdAndUserId(Long tourId, Long userId);

    List<Tour_Score> findAllByTourId(Long tourId);

}
