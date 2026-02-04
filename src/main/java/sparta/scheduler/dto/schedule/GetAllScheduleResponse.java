package sparta.scheduler.dto.schedule;

import lombok.Getter;

import java.util.List;

@Getter
public class GetAllScheduleResponse {
    private final List<ScheduleDto> schedules;

    public GetAllScheduleResponse(List<ScheduleDto> schedules) {
        this.schedules = schedules;
    }
}
