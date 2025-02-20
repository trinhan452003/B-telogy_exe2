package biteology.project.dto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseDTORequest {
    @NotBlank(message = "Name can not be empty !")
    private String name;
    private String description;
}
