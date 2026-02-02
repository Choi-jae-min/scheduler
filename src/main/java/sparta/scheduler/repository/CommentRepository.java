package sparta.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.scheduler.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findALLByScheduleId(Long scheduleId);
}
