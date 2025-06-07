package com.devtracker.common.service;

import com.devtracker.common.model.dto.BaseDto;
import com.devtracker.common.model.entity.BaseEntity;
import com.devtracker.common.util.NoContent;
import com.devtracker.common.util.ServiceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.UUID;

public interface BaseService<T extends BaseEntity, D extends BaseDto> {
    public ServiceResponse<List<D>> get();
    public ServiceResponse<D> getSingle(UUID id);
    public ServiceResponse<D> create(D dto) throws JsonProcessingException;
    public ServiceResponse<D> update(D dto, UUID id);
    public ServiceResponse<NoContent> delete(UUID id);
}
