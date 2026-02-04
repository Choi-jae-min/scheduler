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

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ResponseDto<CreateScheduleResponse>> create(@RequestBody CreateScheduleRequest request) {
        try {
            Long createdScheduleId = scheduleService.createSchedule(request);
            CreateScheduleResponse response = new CreateScheduleResponse(createdScheduleId);
            return ResponseEntity.status(HttpStatus.CREATED).body(DataResponseDto.of("성공" , response));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.of(e.getMessage()));
        }
    }

    @GetMapping("/schedules")
    public ResponseEntity<GetAllScheduleResponse> getAllByPoster(@RequestParam(required = false) String poster){
        if(poster == null || poster.isBlank()){
            GetAllScheduleResponse result = scheduleService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        GetAllScheduleResponse result = scheduleService.getAllByPoster(poster);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId){
        GetScheduleResponse result = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @RequestHeader("x-Scheduler-Password") String password,
            @PathVariable Long scheduleId ,
            @RequestBody UpdateScheduleRequest request) {
        Boolean isValidPassword = scheduleService.checkValidPassword(scheduleId , password);
        if(!isValidPassword){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UpdateScheduleResponse result = scheduleService.updateSchedule(scheduleId , request);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<DeleteScheduleResponse> deleteSchedule(
            @RequestHeader("x-Scheduler-Password") String password,
            @PathVariable Long scheduleId) {
        Boolean isValidPassword = scheduleService.checkValidPassword(scheduleId , password);
        if(!isValidPassword){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        DeleteScheduleResponse result = scheduleService.deleteSchedule(scheduleId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
