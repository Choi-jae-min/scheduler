package sparta.scheduler.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CommentDto {
    private Long id;
    private String content;
    private String poster;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
