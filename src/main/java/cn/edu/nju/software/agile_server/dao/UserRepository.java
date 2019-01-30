package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long userId);

    @Query(value = "select id from #{#entityName} where openid=?1")
    Long findUserIdByOpenId(String openid);

}
