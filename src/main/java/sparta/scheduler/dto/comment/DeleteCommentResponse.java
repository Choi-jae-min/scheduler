package sparta.scheduler.dto.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteCommentResponse {
    private final Long deletedId;
}
