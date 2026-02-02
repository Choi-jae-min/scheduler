package sparta.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduler.dto.CreateScheduleRequest;
import sparta.scheduler.dto.CreateScheduleResponse;
import sparta.scheduler.dto.GetAllScheduleResponse;
import sparta.scheduler.dto.ScheduleDto;
import sparta.scheduler.entity.Schedule;
import sparta.scheduler.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public GetAllScheduleResponse getAll() {
       List<Schedule> schedules = scheduleRepository.findAll();
       List<ScheduleDto> scheduleDtos = new ArrayList<>();
       for (Schedule schedule : schedules) {
           ScheduleDto scheduleDto = new ScheduleDto(
                   schedule.getId(),
                   schedule.getTitle(),
                   schedule.getContent(),
                   schedule.getPoster(),
                   schedule.getCreatedAt(),
                   schedule.getLastModifiedAt()
           );
           scheduleDtos.add(scheduleDto);
       }
       return new GetAllScheduleResponse("성공적으로 조회 되었습니다." , scheduleDtos);
    }
}
