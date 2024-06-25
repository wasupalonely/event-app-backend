package com.backend.eventsapp.eventapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.backend.eventsapp.eventapp.models.entities.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}
