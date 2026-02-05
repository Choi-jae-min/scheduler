package sparta.scheduler.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
    @NotBlank
    @Size(max = 100, message = "댓글은 최대 100자까지 입력할 수 있습니다.")
    private String content;
    @NotBlank
    private String poster;
    @NotBlank
    private String password;
    @NotBlank
    private Long scheduleId;

}
