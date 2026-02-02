package sparta.scheduler.dto.schedule;

import lombok.Getter;

@Getter
public class CreateScheduleResponse {
    private final String message;
    private final Long Id;

    public CreateScheduleResponse(String message, Long Id) {
        this.message = message;
        this.Id = Id;
    }
}
