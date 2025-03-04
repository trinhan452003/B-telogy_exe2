package biteology.project.mapper;


import biteology.project.dto.request.UserProfileDTORequest;
import biteology.project.entity.Disease;
import biteology.project.entity.UserProfile;
import org.mapstruct.*;

@Mapper(config = MapperConfig.class)
public interface UserProfileMapper extends EntityMapper<UserProfileDTORequest, UserProfile> {

    @Mapping(target = "disease", source = "disease", qualifiedByName = "mapDiseaseToEntity")
    UserProfile toEntity(UserProfileDTORequest dto);

    @Mapping(target = "disease", source = "disease", qualifiedByName = "mapDiseaseToString")
    UserProfileDTORequest toDto(UserProfile entity);

    @Mapping(target = "disease", source = "disease", qualifiedByName = "mapDiseaseToEntity")
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget UserProfile entity, UserProfileDTORequest dto);

    @Named("mapDiseaseToEntity")
    default Disease mapDiseaseToEntity(String diseaseId) {
        if (diseaseId == null) return null;
        Disease disease = new Disease();
        disease.setId(diseaseId);
        return disease;
    }

    @Named("mapDiseaseToString")
    default String mapDiseaseToString(Disease disease) {
        return (disease != null) ? disease.getId() : null;
    }
}

