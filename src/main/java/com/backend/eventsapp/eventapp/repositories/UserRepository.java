package com.backend.eventsapp.eventapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.backend.eventsapp.eventapp.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsernameOrEmail(String username, String email);

    User findByUsername(String username);

    User findByEmail(String email);
}
