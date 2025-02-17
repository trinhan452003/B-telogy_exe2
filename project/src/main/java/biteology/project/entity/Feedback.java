package biteology.project.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Feedback")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Feedback extends AbstractAuditingEntity<String>{

    @Column(name = "like_count")
    int like = 0;

    @Column(name = "body", length = 300, nullable = false)
    String body;

    @ManyToOne
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private Content content;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account user;

}
