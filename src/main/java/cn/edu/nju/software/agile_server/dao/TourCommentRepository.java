package cn.edu.nju.software.agile_server.dao;

import cn.edu.nju.software.agile_server.entity.Tour_Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourCommentRepository extends JpaRepository<Tour_Comment, Long> {

    List<Tour_Comment> findAllByTourIdAndUserId(Long tourId, Long userId);
}
