package sparta.scheduler.dto.schedule;

import lombok.Getter;

import java.util.List;

@Getter
public class GetAllScheduleResponse {
    private final String message;
    private final List<ScheduleDto> schedules;

    public GetAllScheduleResponse( String message, List<ScheduleDto> schedules) {
        this.message = message;
        this.schedules = schedules;
    }
}
