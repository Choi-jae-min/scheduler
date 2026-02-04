package sparta.scheduler.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateScheduleResponse {
    private final ScheduleDto schedule;
}
