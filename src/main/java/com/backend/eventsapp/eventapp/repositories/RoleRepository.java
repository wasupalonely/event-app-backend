package com.backend.eventsapp.eventapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.backend.eventsapp.eventapp.models.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
