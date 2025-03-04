package biteology.project.service.impl;


import biteology.project.common.utils.ContextHolderUtils;
import biteology.project.dto.request.DiseaseDTORequest;
import biteology.project.dto.response.FoodDTOResponse;
import biteology.project.entity.Account;
import biteology.project.entity.Disease;
import biteology.project.entity.Food;
import biteology.project.mapper.DiseaseMapper;
import biteology.project.mapper.FoodMapper;
import biteology.project.repository.DiseaseRepository;
import biteology.project.service.DiseaseService;

import biteology.project.web.error.ExceptionDefine.ConflictException;
import biteology.project.web.error.ExceptionDefine.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

public class DiseaseServiceImpl implements DiseaseService {

    DiseaseRepository _repo;
    DiseaseMapper diseaseMapper;
    FoodMapper foodMapper;


    @Override
    public List<Disease> getAllDiseases() {
        return _repo.findAll();
    }


    @Override
    public DiseaseDTORequest createADisease(@NonNull DiseaseDTORequest dtoRequest) {
        try{
            final Disease newDisease = diseaseMapper.toEntity(dtoRequest);
            return diseaseMapper.toDto(_repo.save(newDisease));
        }catch (Exception e){
            throw new ConflictException(HttpStatus.OK.getReasonPhrase(), "Duplicated Name Of Disease");
        }

    }


    @Override
    public void deleteDiseases(@NonNull List<String> ids) {
        _repo.deleteAllByIdInBatch(ids);
    }


    @Override
    public Set<FoodDTOResponse> getFoodsForADisease(@NonNull String id) {
         Disease disease = _repo.findById(id) .orElseThrow(()->
                 new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase(), "Id not found"));
        Set<Food> newSet = disease.getFoods();
         return foodMapper.toDto(newSet);

    }


}
