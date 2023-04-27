package com.bitirme.BitirmeProjesi.dtomapper;

import com.bitirme.BitirmeProjesi.dto.StudentDto;
import com.bitirme.BitirmeProjesi.entity.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudentDtoMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromDto(StudentDto dto, @MappingTarget Student entity);
}
