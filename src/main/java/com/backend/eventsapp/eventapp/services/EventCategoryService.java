package com.backend.eventsapp.eventapp.services;

import org.springframework.stereotype.Service;

import com.backend.eventsapp.eventapp.models.entities.EventCategory;
import com.backend.eventsapp.eventapp.repositories.EventCategoryRepository;
import com.backend.eventsapp.eventapp.services.common.AbstractCrudService;

@Service
public class EventCategoryService extends AbstractCrudService<EventCategory, Long, EventCategoryRepository> {

}
