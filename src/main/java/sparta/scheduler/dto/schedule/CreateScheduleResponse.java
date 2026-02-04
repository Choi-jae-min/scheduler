package sparta.scheduler.dto.schedule;

import lombok.Getter;

@Getter
public class CreateScheduleResponse {
    private final Long Id;

    public CreateScheduleResponse(Long Id) {
        this.Id = Id;
    }
}
