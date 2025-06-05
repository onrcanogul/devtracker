package com.devtracker.logservice.controller;

import com.devtracker.common.util.NoContent;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.logservice.dto.LogDto;
import com.devtracker.logservice.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/log")
public class LogController {

    private final LogService service;

    public LogController(LogService service) {
        this.service = service;
    }

    @GetMapping("get-all")
    public ResponseEntity<ServiceResponse<List<LogDto>>> get() {
        ServiceResponse<List<LogDto>> response = service.get();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<ServiceResponse<LogDto>> getById(@PathVariable UUID id) {
        ServiceResponse<LogDto> response = service.getSingle(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("create")
    public ResponseEntity<ServiceResponse<LogDto>> create(@RequestBody LogDto model) {
        ServiceResponse<LogDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("update")
    public ResponseEntity<ServiceResponse<LogDto>> update(@RequestBody LogDto model) {
        ServiceResponse<LogDto> response = service.update(model, model.getUserId());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
