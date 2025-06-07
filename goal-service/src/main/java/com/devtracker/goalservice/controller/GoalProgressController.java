package com.devtracker.goalservice.controller;

import com.devtracker.common.util.NoContent;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.goalservice.dto.GoalDto;
import com.devtracker.goalservice.dto.GoalProgressDto;
import com.devtracker.goalservice.service.GoalProgressService;
import com.devtracker.goalservice.service.GoalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/goal-progress")
public class GoalProgressController {
    private final GoalProgressService service;

    public GoalProgressController(GoalProgressService service) {
        this.service = service;
    }

    @GetMapping("get-all")
    public ResponseEntity<ServiceResponse<List<GoalProgressDto>>> get() {
        ServiceResponse<List<GoalProgressDto>> response = service.get();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<ServiceResponse<GoalProgressDto>> getById(@PathVariable UUID id) {
        ServiceResponse<GoalProgressDto> response = service.getSingle(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("create")
    public ResponseEntity<ServiceResponse<GoalProgressDto>> create(@RequestBody GoalProgressDto model) throws JsonProcessingException {
        ServiceResponse<GoalProgressDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("update")
    public ResponseEntity<ServiceResponse<GoalProgressDto>> update(@RequestBody GoalProgressDto model) {
        ServiceResponse<GoalProgressDto> response = service.update(model, model.getId());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
