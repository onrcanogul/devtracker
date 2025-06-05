package com.devtracker.trackingservice.controller;

import com.devtracker.common.util.NoContent;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.trackingservice.dto.TrackingSummaryDto;
import com.devtracker.trackingservice.service.TrackingSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/daily-tracking")
@RestController
public class TrackingSummaryController {
    private final TrackingSummaryService service;

    public TrackingSummaryController(TrackingSummaryService service) {
        this.service = service;
    }

    @GetMapping("get-all")
    public ResponseEntity<ServiceResponse<List<TrackingSummaryDto>>> get() {
        ServiceResponse<List<TrackingSummaryDto>> response = service.get();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<ServiceResponse<TrackingSummaryDto>> getById(@PathVariable UUID id) {
        ServiceResponse<TrackingSummaryDto> response = service.getSingle(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("create")
    public ResponseEntity<ServiceResponse<TrackingSummaryDto>> create(@RequestBody TrackingSummaryDto model) {
        ServiceResponse<TrackingSummaryDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("update")
    public ResponseEntity<ServiceResponse<TrackingSummaryDto>> update(@RequestBody TrackingSummaryDto model) {
        ServiceResponse<TrackingSummaryDto> response = service.update(model, model.getId());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
