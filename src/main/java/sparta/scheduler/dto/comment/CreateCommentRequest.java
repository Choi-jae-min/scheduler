package sparta.scheduler.dto.comment;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String content;
    private String poster;
    private String password;
    private Long scheduleId;
}
