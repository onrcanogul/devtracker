package com.devtracker.githubservice.controller;

import com.devtracker.common.util.NoContent;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.githubservice.dto.GithubAccountDto;
import com.devtracker.githubservice.service.GithubAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/github-account")
public class GithubAccountController {
    private final GithubAccountService service;

    public GithubAccountController(GithubAccountService service) {
        this.service = service;
    }

    @GetMapping("get-all")
    public ResponseEntity<ServiceResponse<List<GithubAccountDto>>> get() {
        ServiceResponse<List<GithubAccountDto>> response = service.get();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<ServiceResponse<GithubAccountDto>> getById(@PathVariable UUID id) {
        ServiceResponse<GithubAccountDto> response = service.getSingle(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("create")
    public ResponseEntity<ServiceResponse<GithubAccountDto>> create(@RequestBody GithubAccountDto model) throws JsonProcessingException {
        ServiceResponse<GithubAccountDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("update")
    public ResponseEntity<ServiceResponse<GithubAccountDto>> update(@RequestBody GithubAccountDto model) {
        ServiceResponse<GithubAccountDto> response = service.update(model, model.getId());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
