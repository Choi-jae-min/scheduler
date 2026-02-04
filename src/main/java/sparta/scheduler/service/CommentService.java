package sparta.scheduler.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.scheduler.dto.comment.CreateCommentRequest;
import sparta.scheduler.dto.comment.CreateCommentResponse;
import sparta.scheduler.dto.comment.DeleteCommentResponse;
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

    public void checkCommentCnt(Long scheduleId) {
        scheduleService.checkSchedule(scheduleId);
        List<Comment> commentList = commentRepository.findALLByScheduleId(scheduleId);

        if (commentList.size() >= 10) {
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

    public DeleteCommentResponse deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 댓글입니다.")
        );

        commentRepository.delete(comment);

        return new DeleteCommentResponse(commentId , "성공적으로 삭제 되었습니다.");
    }

    public CreateCommentResponse createComment(CreateCommentRequest createCommentRequest){
        Comment comment = new Comment(
                createCommentRequest.getContent(),
                createCommentRequest.getPoster(),
                createCommentRequest.getPassword(),
                createCommentRequest.getScheduleId()
        );
        commentRepository.save(comment);

        return new CreateCommentResponse("댓글 등록 성공" , comment.getId(), comment.getScheduleId());
    }
}
