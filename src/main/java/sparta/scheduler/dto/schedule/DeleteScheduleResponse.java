package sparta.scheduler.dto.schedule;

import lombok.Getter;

@Getter
public class DeleteScheduleResponse {
    private final Long deletedId;

    public DeleteScheduleResponse(Long deletedId) {
        this.deletedId = deletedId;
    }
}
