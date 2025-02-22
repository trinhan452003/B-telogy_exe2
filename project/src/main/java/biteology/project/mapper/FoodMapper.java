package biteology.project.mapper;

import biteology.project.dto.response.FoodDTOResponse;
import biteology.project.entity.Food;
import org.mapstruct.Mapper;

@Mapper(
        config = MapperConfig.class
)
public interface FoodMapper extends EntityMapper<FoodDTOResponse, Food> {
}
