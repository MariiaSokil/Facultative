package com.epam.mappers;

import java.util.Collection;


public interface BaseMapper<DTO,MODEL> {
    DTO toDTO(MODEL model);
    MODEL toMODEL(DTO dto);
    Collection<MODEL> toMODEL(Collection<DTO> dtos);
    Collection<DTO> toDTO(Collection<MODEL> models);
}
