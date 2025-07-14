package com.flipr.fullstacktask.controller;

import com.flipr.fullstacktask.model.Client;
import com.flipr.fullstacktask.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientRepository repo;

    public ClientController(ClientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Client> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody @Valid Client c) {
        return new ResponseEntity<>(repo.save(c), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getOne(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id,
                                         @RequestBody @Valid Client c) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setName(c.getName());
                    existing.setLogoUrl(c.getLogoUrl());
                    return new ResponseEntity<>(repo.save(existing), HttpStatus.OK);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
