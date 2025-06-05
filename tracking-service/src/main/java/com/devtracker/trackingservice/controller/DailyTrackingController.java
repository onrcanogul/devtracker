package com.devtracker.trackingservice.controller;

import com.devtracker.common.util.NoContent;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.trackingservice.dto.DailyTrackingDto;
import com.devtracker.trackingservice.service.DailyTrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/daily-tracking")
@RestController
public class DailyTrackingController {
    private final DailyTrackingService service;

    public DailyTrackingController(DailyTrackingService service) {
        this.service = service;
    }

    @GetMapping("get-all")
    public ResponseEntity<ServiceResponse<List<DailyTrackingDto>>> get() {
        ServiceResponse<List<DailyTrackingDto>> response = service.get();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<ServiceResponse<DailyTrackingDto>> getById(@PathVariable UUID id) {
        ServiceResponse<DailyTrackingDto> response = service.getSingle(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("create")
    public ResponseEntity<ServiceResponse<DailyTrackingDto>> create(@RequestBody DailyTrackingDto model) {
        ServiceResponse<DailyTrackingDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("update")
    public ResponseEntity<ServiceResponse<DailyTrackingDto>> update(@RequestBody DailyTrackingDto model) {
        ServiceResponse<DailyTrackingDto> response = service.update(model, model.getId());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
