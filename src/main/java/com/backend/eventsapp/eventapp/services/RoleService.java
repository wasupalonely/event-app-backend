package com.backend.eventsapp.eventapp.services;

import org.springframework.stereotype.Service;

import com.backend.eventsapp.eventapp.models.entities.Role;
import com.backend.eventsapp.eventapp.repositories.RoleRepository;
import com.backend.eventsapp.eventapp.services.common.AbstractCrudService;

@Service
public class RoleService extends AbstractCrudService<Role, Long, RoleRepository> {

}
