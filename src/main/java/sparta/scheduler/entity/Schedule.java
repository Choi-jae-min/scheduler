package sparta.scheduler.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparta.scheduler.dto.schedule.UpdateScheduleRequest;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Schedules")
public class Schedule extends BaseEntity {

    private static final int MAX_TITLE_LENGTH = 30;
    private static final int MAX_CONTENT_LENGTH = 200;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = MAX_TITLE_LENGTH, nullable = false)
    private String title;

    @Column(length = MAX_CONTENT_LENGTH, nullable = false)
    private String content;

    @Column(nullable = false)
    private String poster;

    @Column(nullable = false)
    private String password;

    public Schedule(String title, String content, String poster, String password) {
        this.title = title;
        this.content = content;
        this.poster = poster;
        this.password = password;
    }

    public void update(UpdateScheduleRequest req) {
        if (req.hasTitle()) {
            this.title = req.getTitle();
        }
        if (req.hasPoster()) {
            this.poster = req.getPoster();
        }
    }

}
