package sparta.scheduler.dto.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetScheduleResponse {
    private final ScheduleWithCommentDto schedule;
}
