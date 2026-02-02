package sparta.scheduler.dto.schedule;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String poster;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;


    public ScheduleDto(Long id ,String title, String content, String poster, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.poster = poster;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
