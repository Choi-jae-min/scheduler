package sparta.scheduler.dto;

import lombok.Getter;

@Getter
public class CreateScheduleResponse {
    private final String message;
    private Long Id;

    public CreateScheduleResponse(String message, Long Id) {
        this.message = message;
        this.Id = Id;
    }

    public CreateScheduleResponse(String message) {
        this.message = message;
    }
}
