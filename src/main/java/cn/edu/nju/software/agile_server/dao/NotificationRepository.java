package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query(value = "select * from t_notification where f_user_id=?1",nativeQuery = true)
    List<Notification> findAllByUserId(Long userId);
}
