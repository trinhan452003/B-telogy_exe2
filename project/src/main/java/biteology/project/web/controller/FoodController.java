package biteology.project.web.controller;


import biteology.project.dto.request.FoodDTORequest;
import biteology.project.dto.response.Response;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/createFood", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Response<?> createAFood( @RequestPart("name") String name,
                             @RequestPart("description") String description,
                             @RequestPart("imageUrl") MultipartFile imageUrl);

    @PostMapping("/{foodId}/assignDiseaseForAFood")
    @ResponseStatus(HttpStatus.OK)
    Response<?> assignDiseaseForAFood(@NonNull @PathVariable final String foodId, @RequestBody final Set<String> diseaseIds);
}
