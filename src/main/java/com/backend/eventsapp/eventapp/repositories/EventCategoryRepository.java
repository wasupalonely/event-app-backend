package com.backend.eventsapp.eventapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.backend.eventsapp.eventapp.models.entities.EventCategory;

public interface EventCategoryRepository extends CrudRepository<EventCategory, Long> {

}
