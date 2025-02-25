package biteology.project.web.controller.impl;

import biteology.project.dto.request.FoodDTORequest;
import biteology.project.dto.response.Response;
import biteology.project.service.FoodService;
import biteology.project.web.controller.FoodController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Food API", description = "APIs for managing foods")
public class FoodControllerImpl implements FoodController {

    FoodService foodService;

    @Override
    @Operation(summary = "Get all food", description = "Retrieve a list of all food")
    public Response<?> getAllFoods() {
        log.info("=================Request Get All Foods =================");

        return Response.ok(foodService.getAllFoods());
    }

    @Override
    @Operation(summary = "Delete many food", description = "Delete food by their ids (need ROLE DOCTOR)")
    public Response<Void> deleteFoods(List<String> ids) {
        log.info("=================Request Delete Foods =================");
        foodService.deleteFoods(ids);
        return Response.noContent();
    }


    @Override
    @Operation(summary = "Create a food", description = "Create a food (need ROLE DOCTOR)")
    public Response<?> createAFood(FoodDTORequest foodDTORequest) {
        log.info("=================Request Create A Food =================");
        return Response.created(foodService.createAFood(foodDTORequest));
    }

    @Override
    @Operation(summary = "Assign a food to diseases", description = "Assign a food to many diseases (need ROLE DOCTOR)")
    public Response<?> assignDiseaseForAFood(@NonNull String foodId, Set<String> diseaseIds) {
        log.info("=================Request Assign For  Food :{} =================", foodId);
        return Response.ok(foodService.assignDiseaseForAFood(foodId, diseaseIds));
    }


}
