package sparta.scheduler.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.scheduler.dto.*;
import sparta.scheduler.service.ScheduleService;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<CreateScheduleResponse> create(@RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse result = scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schedule")
    public ResponseEntity<GetAllScheduleResponse> getAll(){
        GetAllScheduleResponse result = scheduleService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId){
        GetScheduleResponse result = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
