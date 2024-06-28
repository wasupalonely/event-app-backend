package com.backend.eventsapp.eventapp.services;

import com.backend.eventsapp.eventapp.models.entities.Role;
import com.backend.eventsapp.eventapp.models.entities.User;
import com.backend.eventsapp.eventapp.repositories.RoleRepository;
import com.backend.eventsapp.eventapp.repositories.UserRepository;
import com.backend.eventsapp.eventapp.services.common.AbstractCrudService;
import com.backend.eventsapp.eventapp.utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends AbstractCrudService<User, Long, UserRepository> implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> role = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        if (role.isPresent()) {
            roles.add(role.orElseThrow());
        }

        user.setRoles(roles);

        return super.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", usernameOrEmail)));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }

    @Override
    @Transactional
    public User update(Long id, User entity) {
        User user = super.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        BeanUtils.copyProperties(entity, user, Utils.getNullAndExcludedPropertyNames(entity, "id", "password"));

        return super.save(user);
    }
}
