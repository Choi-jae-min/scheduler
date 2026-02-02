package sparta.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.scheduler.dto.comment.CreateCommentRequest;
import sparta.scheduler.dto.comment.CreateCommentResponse;
import sparta.scheduler.entity.Comment;
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
            throw new IllegalStateException("해당 일정에 댓글이 10개 이상입니다.");
        }
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
