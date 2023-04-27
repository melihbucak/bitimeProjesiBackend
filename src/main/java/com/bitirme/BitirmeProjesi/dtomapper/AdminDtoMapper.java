package com.bitirme.BitirmeProjesi.dtomapper;

import com.bitirme.BitirmeProjesi.dto.AdminDto;
import com.bitirme.BitirmeProjesi.entity.Admin;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdminDtoMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdminFromDto(AdminDto dto, @MappingTarget Admin entity);
}