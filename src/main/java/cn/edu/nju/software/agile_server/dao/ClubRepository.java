package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}