
package com.bitirme.BitirmeProjesi.dtomapper;

import com.bitirme.BitirmeProjesi.dto.YoklamaDto;
import com.bitirme.BitirmeProjesi.entity.Course;
import com.bitirme.BitirmeProjesi.entity.Yoklama;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface YoklamaDtoMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAttendanceFromDto(YoklamaDto dto, @MappingTarget Yoklama entity);
    default Course map(Long value) {
        if (value == null) {
            return null;
        }
        Course course = new Course();
        course.setDersKodu(value);
        return course;
    }
}