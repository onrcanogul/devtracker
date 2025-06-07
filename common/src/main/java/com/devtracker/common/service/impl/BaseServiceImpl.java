package com.devtracker.common.service.impl;

import com.devtracker.common.exception.NotFoundException;
import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.model.dto.BaseDto;
import com.devtracker.common.model.entity.BaseEntity;
import com.devtracker.common.repository.BaseRepository;
import com.devtracker.common.service.BaseService;
import com.devtracker.common.util.NoContent;
import com.devtracker.common.util.ServiceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public abstract class BaseServiceImpl<T extends BaseEntity, D extends BaseDto> implements BaseService<T, D> {
    private final BaseRepository<T> repository;
    private final Mapper<T, D> mapper;
    public BaseServiceImpl(BaseRepository<T> repository, Mapper<T, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ServiceResponse<List<D>> get() {
        return ServiceResponse.success(repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList()), 200);
    }

    public ServiceResponse<D> getSingle(UUID id) {
        return ServiceResponse.success(mapper.toDto(repository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"))), 200);
    }

    public ServiceResponse<D> create(D dto) throws JsonProcessingException {
        T entity = mapper.toEntity(dto);
        T newEntity = repository.save(entity);
        return ServiceResponse.success(mapper.toDto(newEntity), 201);
    }

    public ServiceResponse<D> update(D dto, UUID id) {
        T entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        updateEntity(dto, entity);
        repository.save(entity);
        return ServiceResponse.success(dto, 200);
    }

    public ServiceResponse<NoContent> delete(UUID id) {
        T entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        entity.setDeleted(true);
        repository.save(entity);
        return ServiceResponse.success(204);
    }

    protected abstract void updateEntity(D dto, T entity);
}
