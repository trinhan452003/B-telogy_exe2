package biteology.project.service.impl;


import biteology.project.dto.request.DiseaseDTORequest;
import biteology.project.entity.Disease;
import biteology.project.mapper.DiseaseMapper;
import biteology.project.repository.DiseaseRepository;
import biteology.project.service.DiseaseService;
import biteology.project.web.error.ExceptionDefine.ConflictException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DiseaseServiceImpl implements DiseaseService {

    DiseaseRepository _repo;
    private final DiseaseMapper diseaseMapper;

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


}
