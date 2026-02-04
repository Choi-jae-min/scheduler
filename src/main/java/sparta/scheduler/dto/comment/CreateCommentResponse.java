package sparta.scheduler.dto.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCommentResponse {
    private final Long id;
    private final Long ScheduleId;
}
