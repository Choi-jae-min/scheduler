package sparta.scheduler.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name ="comments")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    private static final int MAX_CONTENT_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = MAX_CONTENT_LENGTH, nullable = false)
    private String content;

    @Column(nullable = false)
    private String poster;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false) // 조건 - 매핑 관계 사용 X
    private Long scheduleId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    public Comment(String content, String poster, String password, Long scheduleId) {
        this.content = content;
        this.poster = poster;
        this.password = password;
        this.scheduleId = scheduleId;
    }
}
