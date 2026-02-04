package sparta.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.scheduler.dto.comment.CommentDto;
import sparta.scheduler.dto.schedule.*;
import sparta.scheduler.entity.Comment;
import sparta.scheduler.entity.Schedule;
import sparta.scheduler.repository.CommentRepository;
import sparta.scheduler.repository.ScheduleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest scheduleRequest) {
        Schedule schedule = new Schedule(
                scheduleRequest.getTitle(),
                scheduleRequest.getContent(),
                scheduleRequest.getPoster(),
                scheduleRequest.getPassword()
        );
        scheduleRepository.save(schedule);

        return new CreateScheduleResponse("성공적으로 생성 되었습니다." , schedule.getId());
    }

    @Transactional(readOnly = true)
    public GetAllScheduleResponse getAll() {
        List<Schedule> schedules = scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifiedAt"));
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleDto scheduleDto = new ScheduleDto(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getPoster(),
                    schedule.getCreatedAt(),
                    schedule.getLastModifiedAt()
            );
            scheduleDtos.add(scheduleDto);
        }
        return new GetAllScheduleResponse("성공적으로 조회 되었습니다." , scheduleDtos);
    }
    @Transactional(readOnly = true)
    public GetAllScheduleResponse getAllByPoster(String poster) {
        List<Schedule> schedules = scheduleRepository.findAllByPoster(poster,Sort.by(Sort.Direction.DESC, "lastModifiedAt"));
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleDto scheduleDto = new ScheduleDto(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getPoster(),
                    schedule.getCreatedAt(),
                    schedule.getLastModifiedAt()
            );
            scheduleDtos.add(scheduleDto);
        }
        return new GetAllScheduleResponse("성공적으로 조회 되었습니다." , scheduleDtos);
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        List<Comment> commentList = commentRepository.findALLByScheduleId(scheduleId);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDto commentDto = new CommentDto(
                    comment.getId(),
                    comment.getContent(),
                    comment.getPoster(),
                    comment.getCreatedAt(),
                    comment.getLastModifiedAt()
            );
            commentDtos.add(commentDto);
        }
        ScheduleWithCommentDto scheduleDto = new ScheduleWithCommentDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getPoster(),
                commentDtos,
                schedule.getCreatedAt(),
                schedule.getLastModifiedAt()
        );
        return new GetScheduleResponse("성공적으로 조회 되었습니다",scheduleDto);
    }

    public Boolean checkValidPassword(Long scheduleId, String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        return schedule.getPassword().equals(password);
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        schedule.update(request);
        ScheduleDto scheduleDto = new ScheduleDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getPoster(),
                schedule.getCreatedAt(),
                schedule.getLastModifiedAt()
        );
        return new UpdateScheduleResponse("성공적으로 수정 되었습니다" , scheduleDto);
    }

    @Transactional
    public DeleteScheduleResponse deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("이미 존재하지 않는 일정입니다.")
        );

        scheduleRepository.delete(schedule);
        return new DeleteScheduleResponse(scheduleId,"성공적으로 삭제 되었습니다");
    }

    public void checkSchedule(Long scheduleId){
        scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
    }
}
