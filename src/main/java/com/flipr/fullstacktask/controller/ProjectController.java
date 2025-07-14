package com.flipr.fullstacktask.controller;

import com.flipr.fullstacktask.model.Project;
import com.flipr.fullstacktask.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                                 // marks it as a REST endpoint
@RequestMapping("/api/projects")                // base URL for all methods
@CrossOrigin(origins = "*")                     // allow front‑end calls during dev
public class ProjectController {

    private final ProjectRepository repo;       // DI of repository

    public ProjectController(ProjectRepository repo) { // constructor injection
        this.repo = repo;
    }

    /* ──────────────── 1. GET /api/projects  ──────────────── */
    @GetMapping
    public List<Project> getAll() {
        return repo.findAll();                  // returns JSON list
    }

    /* ──────────────── 2. POST /api/projects ──────────────── */
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody @Valid Project p) {
        Project saved = repo.save(p);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /* ──────────────── 3. GET /api/projects/{id} ──────────── */
    @GetMapping("/{id}")
    public ResponseEntity<Project> getOne(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /* ──────────────── 4. PUT /api/projects/{id} ──────────── */
    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@PathVariable Long id,
                                          @RequestBody @Valid Project p) {

        return repo.findById(id)
                .map(existing -> {
                    existing.setName(p.getName());
                    existing.setDescription(p.getDescription());
                    existing.setImageUrl(p.getImageUrl());
                    return new ResponseEntity<>(repo.save(existing), HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /* ──────────────── 5. DELETE /api/projects/{id} ───────── */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);                    // 204 No Content if id existed
    }
}
