package sparta.scheduler.dto.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GetAllScheduleResponse {
    private final List<ScheduleDto> schedules;
}
