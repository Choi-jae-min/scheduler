package sparta.scheduler.dto.schedule;

import lombok.Getter;

@Getter
public class GetScheduleResponse {
    private final ScheduleWithCommentDto schedule;

    public GetScheduleResponse(ScheduleWithCommentDto schedule) {
        this.schedule = schedule;
    }
}
