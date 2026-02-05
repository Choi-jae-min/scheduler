package sparta.scheduler.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    @NotBlank
    @Size(max = 30, message = "제목은 최대 30자까지 입력할 수 있습니다.")
    private String title;
    @NotBlank
    @Size(max = 200, message = "일정 내용은 최대 200자까지 입력할 수 있습니다.")
    private String content;
    @NotBlank
    private String poster;
    @NotBlank
    private String password;
}
