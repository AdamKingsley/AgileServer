package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.Tour;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {

    Tour findByIdAndState(Long id, Integer state);

    List<Tour> findByState(Integer state);

    @Query(value = "select * from #{#entityName} as t where t.clubId in ?1 and t.state=?2",nativeQuery = true)
    List<Tour> findAllByClubIdExistsAndState(List<Long> clubIds, Integer state);
}
