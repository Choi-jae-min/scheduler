package sparta.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.scheduler.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
