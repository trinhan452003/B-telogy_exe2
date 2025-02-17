package biteology.project.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "UserProfile")
public class UserProfile extends AbstractAuditingEntity<String>{

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "health_condition")
    private String healthCondition;

    @Column(name = "doB")
    private LocalDate dob;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private Account account;

}
