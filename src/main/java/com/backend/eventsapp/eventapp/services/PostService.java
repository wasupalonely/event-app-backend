package com.backend.eventsapp.eventapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.eventsapp.eventapp.models.entities.Post;
import com.backend.eventsapp.eventapp.repositories.PostRepository;
import com.backend.eventsapp.eventapp.services.common.AbstractCrudService;

@Service
public class PostService extends AbstractCrudService<Post, Long, PostRepository> {
    public List<Post> findByEventId(Long eventId) {
        return repository.findByEventId(eventId);
    }
}
