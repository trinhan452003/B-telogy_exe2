package biteology.project.dto.request;

import biteology.project.entity.Account;
import biteology.project.entity.Disease;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserProfileDTORequest {
     String fullName;
     String healthCondition;
     LocalDate dob;
     String phoneNumber;
     String disease;
}
