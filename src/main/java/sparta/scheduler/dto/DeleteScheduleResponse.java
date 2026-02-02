package sparta.scheduler.dto;

import lombok.Getter;

@Getter
public class DeleteScheduleResponse {
    private final Long deletedId;
    private final String message;

    public DeleteScheduleResponse(Long deletedId, String message) {
        this.deletedId = deletedId;
        this.message = message;
    }
}
