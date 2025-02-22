package biteology.project.web.controller.impl;

import biteology.project.dto.request.FoodDTORequest;
import biteology.project.dto.response.Response;
import biteology.project.service.FoodService;
import biteology.project.web.controller.FoodController;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class FoodControllerImpl implements FoodController {

    FoodService foodService;
    @Override
    public Response<?> getAllFoods() {
        log.info("=================Request Get All Foods =================");

        return Response.ok(foodService.getAllFoods());
    }

    @Override
    public Response<Void> deleteFoods(List<String> ids) {
        log.info("=================Request Delete Foods =================");
        foodService.deleteFoods(ids);
        return Response.noContent();
    }

    @Override
    public Response<?> createAFood(FoodDTORequest foodDTORequest) {
        log.info("=================Request Create A Food =================");
        return Response.created(foodService.createAFood(foodDTORequest));
    }

    @Override
    public Response<?> assignDiseaseForAFood(@NonNull String foodId, Set<String> diseaseIds) {
        log.info("=================Request Assign For  Food :{} =================", foodId);
        return Response.ok(foodService.assignDiseaseForAFood(foodId, diseaseIds));
    }


}
