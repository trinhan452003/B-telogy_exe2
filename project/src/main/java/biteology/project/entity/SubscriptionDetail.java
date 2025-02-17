package biteology.project.entity;


import biteology.project.entity.Enum.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "subscription_detail")
public class SubscriptionDetail extends AbstractAuditingEntity<String>
{
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account user;


    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SubscriptionStatus status;

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
}
