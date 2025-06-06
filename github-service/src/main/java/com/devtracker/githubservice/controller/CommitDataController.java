package com.devtracker.githubservice.controller;

import com.devtracker.common.util.NoContent;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.githubservice.dto.CommitDataDto;
import com.devtracker.githubservice.service.CommitDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/commit-data")
public class CommitDataController {
    private final CommitDataService service;

    public CommitDataController(CommitDataService service) {
        this.service = service;
    }

    @GetMapping("get-all")
    public ResponseEntity<ServiceResponse<List<CommitDataDto>>> get() {
        ServiceResponse<List<CommitDataDto>> response = service.get();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<ServiceResponse<CommitDataDto>> getById(@PathVariable UUID id) {
        ServiceResponse<CommitDataDto> response = service.getSingle(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("create")
    public ResponseEntity<ServiceResponse<CommitDataDto>> create(@RequestBody CommitDataDto model) {
        ServiceResponse<CommitDataDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("update")
    public ResponseEntity<ServiceResponse<CommitDataDto>> update(@RequestBody CommitDataDto model) {
        ServiceResponse<CommitDataDto> response = service.update(model, model.getId());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
