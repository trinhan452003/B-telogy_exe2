package biteology.project.service.impl;

import biteology.project.dto.request.FoodDTORequest;
import biteology.project.dto.response.FoodDTOResponse;
import biteology.project.entity.Disease;
import biteology.project.entity.Food;
import biteology.project.mapper.FoodMapper;
import biteology.project.mapper.FoodMapperRequest;
import biteology.project.repository.DiseaseRepository;
import biteology.project.repository.FoodRepository;
import biteology.project.service.FoodService;
import biteology.project.web.error.ExceptionDefine.NotFoundException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FoodServiceImpl implements FoodService {

    FoodRepository _repo;
    DiseaseRepository _diseaseRepo;
    FoodMapper foodMapperResponse;
    FoodMapperRequest foodMapperRequest;
    SupabaseService supabaseService;

    @Override
    public List<Food> getAllFoods() {
        return _repo.findAll();
    }


    @Override
    public void deleteFoods(@NonNull List<String> ids) {
        _repo.deleteAllByIdInBatch(ids);
    }


    @Override
    @Transactional
    public FoodDTOResponse createAFood(String name, String description, MultipartFile imageUrl) {
        try{
            String imageUrlFinal = supabaseService.uploadImage(imageUrl);

            FoodDTORequest foodBuilder = FoodDTORequest.builder()
                    .description(description)
                    .imageUrl(imageUrlFinal)
                    .name(name)
                    .build();

            final Food newFood = foodMapperRequest.toEntity(foodBuilder);

            return foodMapperResponse.toDto(_repo.save(newFood));
        }catch (IOException e){
            throw new RuntimeException("Upload image failed!", e);
        }


    }



    @Override
    @Transactional
    public String assignDiseaseForAFood(@NonNull String id, Set<String> ids) {
        try{
            Food food = _repo.findById(id) .orElseThrow(()->
                    new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase(), "Id not found"));

            List<Disease> diseaseList = _diseaseRepo.findAllById(ids);
            Set<Disease> listDiseases = new HashSet<>(diseaseList);
            for (Disease disease : listDiseases) {
                disease.getFoods().add(food);
            }
            food.getDiseases().addAll(listDiseases);
            return "Assigned Succesfully";
            }catch (Exception e){
                return "Something went wrong !";
            }

    }
}
