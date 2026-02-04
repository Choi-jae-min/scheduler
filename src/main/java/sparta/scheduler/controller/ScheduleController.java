package sparta.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.scheduler.dto.DataResponseDto;
import sparta.scheduler.dto.ErrorResponseDto;
import sparta.scheduler.dto.ResponseDto;
import sparta.scheduler.dto.schedule.*;
import sparta.scheduler.service.ScheduleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ResponseDto<CreateScheduleResponse>> create(@RequestBody CreateScheduleRequest request) {
        try {
            Long createdScheduleId = scheduleService.createSchedule(request);
            CreateScheduleResponse response = new CreateScheduleResponse(createdScheduleId);
            return ResponseEntity.status(HttpStatus.CREATED).body(DataResponseDto.of("성공적으로 일정을 생성하였습니다." , response));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.of(e.getMessage()));
        }
    }

    @GetMapping("/schedules")
    public ResponseEntity<ResponseDto<GetAllScheduleResponse>> getAllByPoster(@RequestParam(required = false) String poster){
        try {
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
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.of(e.getMessage()));
        }
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ResponseDto<GetScheduleResponse>> getSchedule(@PathVariable Long scheduleId){
        try {
            ScheduleWithCommentDto scheduleWithComment = scheduleService.getSchedule(scheduleId);
            GetScheduleResponse getScheduleResponse = new GetScheduleResponse(scheduleWithComment);
            return ResponseEntity.status(HttpStatus.OK).body(DataResponseDto.of("성공적으로 조회하였습니다." ,getScheduleResponse));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.of(e.getMessage()));
        }

    }

    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<ResponseDto<UpdateScheduleResponse>> updateSchedule(
            @RequestHeader("x-Scheduler-Password") String password,
            @PathVariable Long scheduleId ,
            @RequestBody UpdateScheduleRequest request) {
        Boolean isValidPassword = scheduleService.checkValidPassword(scheduleId , password);
        if(!isValidPassword){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponseDto.of("잘못된 비밀번호입니다."));
        }
        try {
            ScheduleDto updatedSchedule = scheduleService.updateSchedule(scheduleId , request);
            UpdateScheduleResponse updateScheduleResponse = new UpdateScheduleResponse(updatedSchedule);
            return ResponseEntity.status(HttpStatus.OK).body(DataResponseDto.of("성공적으로 수정되었습니다." ,updateScheduleResponse));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDto.of(e.getMessage()));
        }
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<ResponseDto<DeleteScheduleResponse>> deleteSchedule(
            @RequestHeader("x-Scheduler-Password") String password,
            @PathVariable Long scheduleId) {

        Boolean isValidPassword = scheduleService.checkValidPassword(scheduleId , password);
        if(!isValidPassword){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponseDto.of("잘못된 비밀번호입니다."));
        }

        try {
            Long deletedScheduleId = scheduleService.deleteSchedule(scheduleId);
            DeleteScheduleResponse deleteScheduleResponse = new DeleteScheduleResponse(deletedScheduleId);
            return ResponseEntity.status(HttpStatus.OK).body(DataResponseDto.of("성공적으로 삭제되었습니다." ,deleteScheduleResponse));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponseDto.of(e.getMessage()));
        }
    }

}
