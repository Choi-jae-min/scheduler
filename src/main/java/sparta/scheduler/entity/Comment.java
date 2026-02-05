package sparta.scheduler.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="comments")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{

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

    public Comment(String content, String poster, String password, Long scheduleId) {
        this.content = content;
        this.poster = poster;
        this.password = password;
        this.scheduleId = scheduleId;
    }
}
