package biteology.project.entity;


import biteology.project.entity.Enum.AccountRole;
import biteology.project.entity.Enum.ContentStatus;
import biteology.project.entity.Enum.ContentType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Content")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Content extends AbstractAuditingEntity<String> {

    @Column(name = "title")
    String title;

    @Column(name = "image")
    String pictureUrl;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    String body;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "like_count" )
    int likeCount= 0;

    @Column(name = "summary", columnDefinition = "TEXT")
    String summary;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    ContentType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    ContentStatus status = ContentStatus.DRAFT;


    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;
}
