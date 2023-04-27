package com.bitirme.BitirmeProjesi.dtomapper;

import com.bitirme.BitirmeProjesi.dto.TeacherDto;
import com.bitirme.BitirmeProjesi.entity.Teacher;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TeacherDtoMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeacherFromDto(TeacherDto dto, @MappingTarget Teacher entity);
}
