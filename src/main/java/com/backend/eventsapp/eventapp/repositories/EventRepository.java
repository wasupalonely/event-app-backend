package com.backend.eventsapp.eventapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.backend.eventsapp.eventapp.models.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
