package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    Club findByIdAndState(Long id, Integer state);

    List<Club> findByState(Integer state);

    @Query(value = "select * from #{#entityName} as t where t.ownerId in ?1 and t.state=?2",nativeQuery = true)
    List<Club> findAllByOwnerIdExistsAndState(List<Long> ownerIds, Integer state);
}
