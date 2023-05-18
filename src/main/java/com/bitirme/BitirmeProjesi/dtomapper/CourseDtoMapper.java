package com.bitirme.BitirmeProjesi.dtomapper;

import com.bitirme.BitirmeProjesi.dto.CourseDto;
import com.bitirme.BitirmeProjesi.entity.Course;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CourseDtoMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCourseFromDto(CourseDto dto, @MappingTarget Course entity);
}