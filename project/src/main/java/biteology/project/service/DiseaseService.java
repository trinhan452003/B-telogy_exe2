package biteology.project.service;

import biteology.project.dto.request.DiseaseDTORequest;
import biteology.project.entity.Disease;
import lombok.NonNull;

import java.util.List;

public interface DiseaseService {

    List<Disease> getAllDiseases();
    DiseaseDTORequest createADisease(DiseaseDTORequest dtoRequest);
    void deleteDiseases(@NonNull final List<String>ids);


}
