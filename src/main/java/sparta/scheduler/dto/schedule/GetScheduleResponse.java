package sparta.scheduler.dto.schedule;

import lombok.Getter;

@Getter
public class GetScheduleResponse {
    private final String message;
    private final ScheduleWithCommentDto schedule;

    public GetScheduleResponse(String message, ScheduleWithCommentDto schedule) {
        this.message = message;
        this.schedule = schedule;
    }
}
