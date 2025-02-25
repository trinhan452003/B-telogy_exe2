package biteology.project.web.controller;


import biteology.project.dto.request.DiseaseDTORequest;
import biteology.project.dto.response.Response;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disease")
public interface DiseaseController {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Response<?> getAllDiseases();

    @PostMapping("/createDisease")
    @ResponseStatus(HttpStatus.CREATED)
    Response<?> createADisease(@RequestBody @Valid DiseaseDTORequest diseaseDTORequest);

    @DeleteMapping("/deleteDisease")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Response<Void> deleteDiseases(@RequestBody final List<String> ids);

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Response<DiseaseDTORequest> updateSet(@RequestBody final DiseaseDTORequest dto);


    @GetMapping("/{diseaseId}/foods")
    @ResponseStatus(HttpStatus.OK)
    Response<?> getFoodsByADisease(@NonNull @PathVariable final String diseaseId);
}
