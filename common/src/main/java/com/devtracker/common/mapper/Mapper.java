package com.devtracker.common.mapper;

import com.devtracker.common.model.dto.BaseDto;
import com.devtracker.common.model.entity.BaseEntity;

public interface Mapper<D extends BaseEntity, T extends BaseDto> {
    T toDto(D entity);
    D toEntity(T dto);
}
