package com.flipr.fullstacktask.controller;

import com.flipr.fullstacktask.model.ContactForm;
import com.flipr.fullstacktask.repository.ContactFormRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-forms")
@CrossOrigin(origins = "*")
public class ContactFormController {

    private final ContactFormRepository repo;

    public ContactFormController(ContactFormRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<ContactForm> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<ContactForm> create(@RequestBody @Valid ContactForm form) {
        ContactForm saved = repo.save(form);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactForm> getOne(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
