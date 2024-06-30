package com.backend.eventsapp.eventapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.backend.eventsapp.eventapp.models.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByEventId(Long eventId);
}
