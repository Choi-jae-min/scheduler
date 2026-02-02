package sparta.scheduler.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String title;
    private String poster;

    public boolean hasTitle() {
        return title != null && !title.isBlank();
    }

    public boolean hasPoster() {
        return poster != null && !poster.isBlank();
    }
}
