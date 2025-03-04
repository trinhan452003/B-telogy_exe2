package biteology.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Disease")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Disease extends AbstractAuditingEntity<String> {

    @Column(nullable = false, unique = true)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @ManyToMany(mappedBy = "diseases", cascade = CascadeType.REMOVE)
    @JsonIgnore
    Set<Food> foods = new HashSet<>();

    @OneToMany(mappedBy = "disease", fetch = FetchType.LAZY)
    @JsonIgnore
    List<UserProfile> userProfiles;

}