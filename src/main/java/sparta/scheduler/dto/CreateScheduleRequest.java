package sparta.scheduler.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private String title;
    private String content;
    private String poster;
    private String password;
}
