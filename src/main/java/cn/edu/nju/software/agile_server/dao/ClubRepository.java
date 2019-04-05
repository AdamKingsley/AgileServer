package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    @Query(value = "select * from t_club as t where t.f_id=?1 and t.f_state=?2",nativeQuery = true)
    Club findByIdAndState(Long id, Integer state);

    List<Club> findByState(Integer state);

    @Query(value = "select * from t_club as t where t.f_owner_id in ?1 and t.f_state=?2",nativeQuery = true)
    List<Club> findAllByOwnerIdExistsAndState(List<Long> ownerIds, Integer state);

}
