package sparta.scheduler.dto.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sparta.scheduler.dto.comment.CommentDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ScheduleWithCommentDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String poster;
    private final List<CommentDto> comments;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;
}
