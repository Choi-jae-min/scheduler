package sparta.scheduler.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.scheduler.dto.comment.CreateCommentRequest;
import sparta.scheduler.dto.comment.CreateCommentResponse;
import sparta.scheduler.entity.Comment;
import sparta.scheduler.exception.CommentLimitExceededException;
import sparta.scheduler.exception.InvalidPasswordException;
import sparta.scheduler.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleService scheduleService;

    private static final int MAX_COMMENTS_CNT = 10;

    public void checkCommentCnt(Long scheduleId) {
        scheduleService.checkSchedule(scheduleId);
        List<Comment> commentList = commentRepository.findALLByScheduleId(scheduleId);

        if (commentList.size() >= MAX_COMMENTS_CNT) {
            throw new CommentLimitExceededException();
        }
    }

    public void checkCommentPassword(Long commentId, String password) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 댓글입니다.")
        );

        if(!comment.getPassword().equals(password)){
            throw new InvalidPasswordException();
        }
    }

    public Long deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 댓글입니다.")
        );

        commentRepository.delete(comment);

        return commentId;
    }

    public CreateCommentResponse createComment(CreateCommentRequest createCommentRequest){
        Comment comment = new Comment(
                createCommentRequest.getContent(),
                createCommentRequest.getPoster(),
                createCommentRequest.getPassword(),
                createCommentRequest.getScheduleId()
        );
        commentRepository.save(comment);
        return new CreateCommentResponse(comment.getId(), comment.getScheduleId());
    }
}
