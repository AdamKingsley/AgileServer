package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.Tour;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {

    Tour findByIdAndState(Long tourId, Integer state);

    List<Tour> findByState(Integer state);
}
