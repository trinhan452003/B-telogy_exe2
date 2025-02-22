package biteology.project.web.controller;


import biteology.project.dto.request.FoodDTORequest;
import biteology.project.dto.response.Response;
import biteology.project.entity.Disease;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/food")
public interface FoodController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Response<?> getAllFoods();

    @DeleteMapping("/deleteFoods")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Response<Void> deleteFoods(@RequestBody final List<String> ids);

    @PostMapping("/createFood")
    @ResponseStatus(HttpStatus.CREATED)
    Response<?> createAFood(@RequestBody @Valid final FoodDTORequest foodDTORequest);

    @PostMapping("/{foodId}/assignDiseaseForAFood")
    @ResponseStatus(HttpStatus.OK)
    Response<?> assignDiseaseForAFood(@NonNull @PathVariable final String foodId, @RequestBody final Set<String> diseaseIds);
}
