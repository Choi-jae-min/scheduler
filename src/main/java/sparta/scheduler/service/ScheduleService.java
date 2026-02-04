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
    public Long createSchedule(CreateScheduleRequest scheduleRequest) {
        Schedule schedule = new Schedule(
                scheduleRequest.getTitle(),
                scheduleRequest.getContent(),
                scheduleRequest.getPoster(),
                scheduleRequest.getPassword()
        );
        scheduleRepository.save(schedule);

        return schedule.getId();
    }

    @Transactional(readOnly = true)
    public List<ScheduleDto> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifiedAt"));
        return convertScheduleDtoList(schedules);
    }

    @Transactional(readOnly = true)
    public List<ScheduleDto> getAllByPoster(String poster) {
        List<Schedule> schedules = scheduleRepository.findAllByPoster(poster,Sort.by(Sort.Direction.DESC, "lastModifiedAt"));
        return convertScheduleDtoList(schedules);
    }

    private List<ScheduleDto> convertScheduleDtoList(List<Schedule> schedules) {
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

        return scheduleDtos;
    }

    @Transactional(readOnly = true)
    public ScheduleWithCommentDto getSchedule(Long scheduleId) {
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
        return new ScheduleWithCommentDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getPoster(),
                commentDtos,
                schedule.getCreatedAt(),
                schedule.getLastModifiedAt()
        );
    }

    public Boolean checkValidPassword(Long scheduleId, String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );

        return schedule.getPassword().equals(password);
    }

    @Transactional
    public ScheduleDto updateSchedule(Long scheduleId, UpdateScheduleRequest request){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        schedule.update(request);
        return new ScheduleDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getPoster(),
                schedule.getCreatedAt(),
                schedule.getLastModifiedAt()
        );
    }

    @Transactional
    public Long deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("이미 존재하지 않는 일정입니다.")
        );

        scheduleRepository.delete(schedule);
        return scheduleId;
    }

    public void checkSchedule(Long scheduleId){
        scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
    }
}
