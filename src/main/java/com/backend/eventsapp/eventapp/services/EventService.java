package com.backend.eventsapp.eventapp.services;

import org.springframework.stereotype.Service;

import com.backend.eventsapp.eventapp.models.entities.Event;
import com.backend.eventsapp.eventapp.repositories.EventRepository;
import com.backend.eventsapp.eventapp.services.common.AbstractCrudService;

@Service
public class EventService extends AbstractCrudService<Event, Long, EventRepository> {
    
}
