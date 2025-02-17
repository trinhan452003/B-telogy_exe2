package biteology.project.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "PaymentMeythod")
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends AbstractAuditingEntity<String> {

    @Column(name = "payment_name" , nullable = false)
    String name;


    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY)
    private List<SubscriptionDetail> subscriptions;
}
