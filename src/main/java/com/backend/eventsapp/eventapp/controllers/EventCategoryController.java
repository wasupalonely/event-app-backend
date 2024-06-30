package com.backend.eventsapp.eventapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.eventsapp.eventapp.models.entities.EventCategory;
import com.backend.eventsapp.eventapp.services.EventCategoryService;
import com.backend.eventsapp.eventapp.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/event-categories")
public class EventCategoryController {
    @Autowired
    private EventCategoryService eventCategoryService;

    @GetMapping
    public ResponseEntity<List<EventCategory>> findAll() {
        return ResponseEntity.ok(eventCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventCategory> findById(@PathVariable Long id) {
        return eventCategoryService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EventCategory user, BindingResult result) {
        if (result.hasErrors()) {
            return Utils.validation(result);
        }

        return ResponseEntity.created(null).body(eventCategoryService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EventCategory eventCategory) {
        try {
            EventCategory updatedEventCat = eventCategoryService.update(id, eventCategory);
            return ResponseEntity.ok(updatedEventCat);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event category not found with id" + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        eventCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
