package com.devtracker.common.model.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseDto {
    private UUID id;
    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;
}
