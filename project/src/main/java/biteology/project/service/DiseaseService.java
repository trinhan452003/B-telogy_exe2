package biteology.project.service;

import biteology.project.dto.request.DiseaseDTORequest;
import biteology.project.dto.response.FoodDTOResponse;
import biteology.project.entity.Disease;
import biteology.project.entity.Food;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

public interface DiseaseService {

    List<Disease> getAllDiseases();
    DiseaseDTORequest createADisease(DiseaseDTORequest dtoRequest);
    void deleteDiseases(@NonNull final List<String>ids);
    Set<FoodDTOResponse> getFoodsForADisease (@NonNull final String id);

}
