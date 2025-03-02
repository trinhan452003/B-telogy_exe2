package biteology.project.service;

import biteology.project.dto.response.FoodDTOResponse;
import biteology.project.entity.Food;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface FoodService {

    List<Food> getAllFoods();

    void deleteFoods(@NonNull final List<String>ids);

    FoodDTOResponse createAFood(String name, String description, MultipartFile imageUrl);

    String assignDiseaseForAFood(@NonNull final String id, Set<String>ids);

}
