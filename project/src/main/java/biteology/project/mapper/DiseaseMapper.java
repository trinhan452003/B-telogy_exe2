package biteology.project.mapper;


import biteology.project.dto.request.DiseaseDTORequest;
import biteology.project.entity.Disease;
import org.mapstruct.Mapper;

@Mapper(
        config = MapperConfig.class
)
public interface DiseaseMapper extends EntityMapper<DiseaseDTORequest, Disease> {


}
