package sparta.scheduler.dto.schedule;

import lombok.Getter;
import sparta.scheduler.dto.comment.CommentDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleWithCommentDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String poster;
    private final List<CommentDto> comments;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;


    public ScheduleWithCommentDto(Long id ,String title, String content, String poster,List<CommentDto> comments,LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.poster = poster;
        this.comments = comments;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
