package sparta.scheduler.dto.comment;

import lombok.Getter;

@Getter
public class CreateCommentResponse {
    private final String message;
    private final Long id;
    private final Long ScheduleId;

    public CreateCommentResponse(String message, Long id, Long ScheduleId) {
        this.message = message;
        this.id = id;
        this.ScheduleId = ScheduleId;
    }

}
