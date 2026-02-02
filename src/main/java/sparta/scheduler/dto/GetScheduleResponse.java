package sparta.scheduler.dto;

import lombok.Getter;

@Getter
public class GetScheduleResponse {
    private final String message;
    private final ScheduleDto schedule;

    public GetScheduleResponse(String message, ScheduleDto schedule) {
        this.message = message;
        this.schedule = schedule;
    }
}
