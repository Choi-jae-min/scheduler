package sparta.scheduler.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.scheduler.dto.DataResponseDto;
import sparta.scheduler.dto.ResponseDto;
import sparta.scheduler.dto.schedule.*;
import sparta.scheduler.service.ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ResponseDto<CreateScheduleResponse>> create(@RequestBody @Valid CreateScheduleRequest request) {
        Long createdScheduleId = scheduleService.createSchedule(request);
        CreateScheduleResponse response = new CreateScheduleResponse(createdScheduleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(DataResponseDto.of("성공적으로 일정을 생성하였습니다." , response));
    }

    @GetMapping("/schedules")
    public ResponseEntity<ResponseDto<GetAllScheduleResponse>> getAllByPoster(@RequestParam(required = false) String poster){
        GetAllScheduleResponse response;
        List<ScheduleDto> scheduleList;

        if(poster == null || poster.isBlank()){
            scheduleList = scheduleService.getAll();
            response = new GetAllScheduleResponse(scheduleList);
            return ResponseEntity.status(HttpStatus.OK).body(DataResponseDto.of("성공적으로 조회하였습니다." ,response));
        }

        scheduleList = scheduleService.getAllByPoster(poster);
        response = new GetAllScheduleResponse(scheduleList);
        return ResponseEntity.status(HttpStatus.OK).body(DataResponseDto.of("성공적으로 조회하였습니다." ,response));
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ResponseDto<GetScheduleResponse>> getSchedule(@PathVariable Long scheduleId){
        ScheduleWithCommentDto scheduleWithComment = scheduleService.getSchedule(scheduleId);
        GetScheduleResponse getScheduleResponse = new GetScheduleResponse(scheduleWithComment);
        return ResponseEntity.status(HttpStatus.OK).body(DataResponseDto.of("성공적으로 조회하였습니다." ,getScheduleResponse));

    }

    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<ResponseDto<UpdateScheduleResponse>> updateSchedule(
            @RequestHeader("x-Scheduler-Password") String password,
            @PathVariable Long scheduleId ,
            @RequestBody UpdateScheduleRequest request) {

        scheduleService.checkValidPassword(scheduleId , password);
        ScheduleDto updatedSchedule = scheduleService.updateSchedule(scheduleId , request);
        UpdateScheduleResponse updateScheduleResponse = new UpdateScheduleResponse(updatedSchedule);
        return ResponseEntity.status(HttpStatus.OK).body(DataResponseDto.of("성공적으로 수정되었습니다." ,updateScheduleResponse));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<ResponseDto<DeleteScheduleResponse>> deleteSchedule(
            @RequestHeader("x-Scheduler-Password") String password,
            @PathVariable Long scheduleId) {

        scheduleService.checkValidPassword(scheduleId , password);

        Long deletedScheduleId = scheduleService.deleteSchedule(scheduleId);
        DeleteScheduleResponse deleteScheduleResponse = new DeleteScheduleResponse(deletedScheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(DataResponseDto.of("성공적으로 삭제되었습니다." ,deleteScheduleResponse));
    }

}
