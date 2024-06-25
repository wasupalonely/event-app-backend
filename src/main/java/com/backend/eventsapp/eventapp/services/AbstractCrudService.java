package com.backend.eventsapp.eventapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<T, ID, R extends CrudRepository<T, ID>> implements CrudService<T, ID> {

    @Autowired
    protected R repository;

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public T update(T entity, ID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Entity not found with id " + id);
        }
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        repository.deleteById(id);
    }
}

