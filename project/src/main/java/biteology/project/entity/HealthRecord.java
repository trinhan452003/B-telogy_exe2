package biteology.project.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Entity
@Table(name ="HealthRecord")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HealthRecord extends AbstractAuditingEntity<String> {

    @Column(name = "weight")
    double weight;

    @Column(name = "blood_sugar", precision = 5, scale = 2)
    BigDecimal bloodSugar;

    @Column(name = "blood_pressure")
    String bloodPressure;


    @Column(name = "BMI", precision = 4, scale = 1)
    BigDecimal BMI;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private Account user;
}
