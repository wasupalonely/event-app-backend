package com.backend.eventsapp.eventapp.services;

import com.backend.eventsapp.eventapp.models.entities.models.User;
import com.backend.eventsapp.eventapp.repositories.UserRepository;

import org.springframework.beans.BeanUtils;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends AbstractCrudService<User, Long, UserRepository> {

    // NOT FOR THE MOMENT!
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    // @Override
    // @Transactional
    // public User save(User entity) {
    //     entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    //     return super.save(entity);
    // }

    @Override
    @Transactional
    public User update(User entity, Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        String[] ignoreProperties = { "id", "password" };
        BeanUtils.copyProperties(entity, user, ignoreProperties);

        return super.save(user);
    }
}
