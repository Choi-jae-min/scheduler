package sparta.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduler.dto.CreateScheduleRequest;
import sparta.scheduler.dto.CreateScheduleResponse;
import sparta.scheduler.entity.Schedule;
import sparta.scheduler.repository.ScheduleRepository;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;


    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest scheduleRequest) {
        Schedule schedule = new Schedule(
                scheduleRequest.getTitle(),
                scheduleRequest.getContent(),
                scheduleRequest.getPoster(),
                scheduleRequest.getPassword()
        );
        scheduleRepository.save(schedule);

        return new CreateScheduleResponse("성공적으로 생성 되었습니다." , schedule.getId());
    }
}
