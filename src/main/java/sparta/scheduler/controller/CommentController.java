package sparta.scheduler.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.scheduler.dto.DataResponseDto;
import sparta.scheduler.dto.ResponseDto;
import sparta.scheduler.dto.comment.*;
import sparta.scheduler.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<ResponseDto<CreateCommentResponse>> createComment(@RequestBody @Valid CreateCommentRequest createCommentRequest) {
        commentService.checkCommentCnt(createCommentRequest.getScheduleId());
        CreateCommentResponse createCommentResponse = commentService.createComment(createCommentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(DataResponseDto.of("댓글 등록에 성공하였습니다." ,createCommentResponse));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ResponseDto<DeleteCommentResponse>> deleteComment(
            @RequestHeader("x-Comment-Password") String password,
            @PathVariable Long commentId
    ) {
        commentService.checkCommentPassword(commentId, password);

        Long deletedCommentId = commentService.deleteComment(commentId);
        DeleteCommentResponse deleteCommentResponse = new DeleteCommentResponse(deletedCommentId);
        return ResponseEntity.status(HttpStatus.OK).body(DataResponseDto.of("성공적으로 댓글을 삭제하였습니다." ,deleteCommentResponse));
    }
}
