package biteology.project.mapper;


import org.mapstruct.*;

import java.util.List;

public interface EntityMapper<D,E> {


    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    D toDto(E e);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    E toEntity(D d);

    List<D> toDto(List<E> e);
    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    List<E> toEntity(List<D> d);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    void partialUpdate(@MappingTarget E entity, D dto);
}
