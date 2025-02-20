package biteology.project.web.controller.impl;


import biteology.project.dto.request.DiseaseDTORequest;
import biteology.project.dto.response.Response;
import biteology.project.service.DiseaseService;
import biteology.project.web.controller.DiseaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DiseaseControllerImpl implements DiseaseController {

    private final DiseaseService diseaseService;

    @Override
    public Response<?> getAllDiseases() {

        log.info("=================Request Get All Diseases =================");
        return Response.ok(diseaseService.getAllDiseases());
    }

    @Override
    public Response<?> createADisease(DiseaseDTORequest diseaseDTORequest) {
        log.info("=================Request Create A Diseases {} =================", diseaseDTORequest.getName());
        return Response.created(diseaseService.createADisease(diseaseDTORequest));
    }

    @Override
    public Response<Void> deleteDiseases(List<String> ids) {
        log.info("=================Request Delete  Diseases =================");
        diseaseService.deleteDiseases(ids);
        return Response.noContent();
    }

    @Override
    public Response<DiseaseDTORequest> updateSet(DiseaseDTORequest dto) {
        return null;
    }
}
