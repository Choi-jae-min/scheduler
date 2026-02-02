package sparta.scheduler.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateScheduleResponse {
    private final String message;
    private final ScheduleDto schedule;
}
