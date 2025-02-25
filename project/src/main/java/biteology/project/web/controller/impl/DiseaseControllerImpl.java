package biteology.project.web.controller.impl;


import biteology.project.dto.request.DiseaseDTORequest;
import biteology.project.dto.response.Response;
import biteology.project.service.DiseaseService;
import biteology.project.web.controller.DiseaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "Disease API", description = "APIs for managing diseases")
public class DiseaseControllerImpl implements DiseaseController {

    private final DiseaseService diseaseService;


    @Operation(summary = "Get all diseases", description = "Retrieve a list of all diseases ")
    @Override
    public Response<?> getAllDiseases() {
        log.info("=================Request Get All Diseases =================");
        return Response.ok(diseaseService.getAllDiseases());
    }

    @Operation(summary = "Create a new disease", description = "Add a new disease (need ROLE DOCTOR)")
    @Override
    public Response<?> createADisease(DiseaseDTORequest diseaseDTORequest) {
        log.info("=================Request Create A Diseases {} =================", diseaseDTORequest.getName());
        return Response.created(diseaseService.createADisease(diseaseDTORequest));
    }

    @Operation(summary = "Delete diseases by IDs", description = "Remove many diseases at a time (need ROLE DOCTOR)")
    @Override
    public Response<Void> deleteDiseases(List<String> ids) {
        log.info("=================Request Delete  Diseases =================");
        diseaseService.deleteDiseases(ids);
        return Response.noContent();
    }

    @Operation(summary = "NOT DONE")
    @Override
    public Response<DiseaseDTORequest> updateSet(DiseaseDTORequest dto) {
        return null;
    }

    @Operation(summary = "Get foods associated with a disease", description = "Retrieve all foods linked to a specific disease ID")
    @Override
    public Response<?> getFoodsByADisease(@NonNull String diseaseId) {
        log.info("=================Request Get Food For  Diseases =================");
        return Response.ok(diseaseService.getFoodsForADisease(diseaseId));
    }
}
