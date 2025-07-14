package com.flipr.fullstacktask.controller;

import com.flipr.fullstacktask.model.Subscriber;
import com.flipr.fullstacktask.repository.SubscriberRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscribers")
@CrossOrigin(origins = "*")
public class SubscriberController {

    private final SubscriberRepository repo;

    public SubscriberController(SubscriberRepository repo) {
        this.repo = repo;
    }

    /* GET all */
    @GetMapping
    public List<Subscriber> getAll() {
        return repo.findAll();
    }

    /* POST – add new */
    @PostMapping
    public ResponseEntity<?> subscribe(@RequestBody @Valid Subscriber s) {
        if (repo.existsByEmail(s.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Email already subscribed");
        }
        return new ResponseEntity<>(repo.save(s), HttpStatus.CREATED);
    }

    /* DELETE */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
