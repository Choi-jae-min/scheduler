package sparta.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sparta.scheduler.dto.comment.CreateCommentRequest;
import sparta.scheduler.dto.comment.CreateCommentResponse;
import sparta.scheduler.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody CreateCommentRequest createCommentRequest) {
        commentService.checkCommentCnt(createCommentRequest.getScheduleId());

        CreateCommentResponse result = commentService.createComment(createCommentRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
