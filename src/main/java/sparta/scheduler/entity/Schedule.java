package sparta.scheduler.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import sparta.scheduler.dto.schedule.UpdateScheduleRequest;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "Schedules")
public class Schedule {

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

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

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
