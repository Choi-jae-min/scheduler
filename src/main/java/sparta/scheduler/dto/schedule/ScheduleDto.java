package sparta.scheduler.dto.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String poster;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;
}
