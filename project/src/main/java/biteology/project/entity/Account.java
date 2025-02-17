package biteology.project.entity;

import biteology.project.entity.Enum.AccountRole;
import biteology.project.entity.Enum.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "Account")
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractAuditingEntity<String>{

    @Column(name = "uuid", unique = true, nullable = false)
    String uuid;

    @Column(name = "password_hash", nullable = false)
    String passwordHash;

    @Column(name = "email")
    String email;

    @Column(name = "avatar")
    String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private AccountRole role = AccountRole.USER;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubscriptionDetail> subscriptionDetails;

    @JsonManagedReference
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProfile profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HealthRecord healthRecord;
}
