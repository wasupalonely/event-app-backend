package com.backend.eventsapp.eventapp.services.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.backend.eventsapp.eventapp.models.entities.User;
import com.backend.eventsapp.eventapp.repositories.UserRepository;

public class JpaUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> o = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (!o.isPresent()) {
            throw new UsernameNotFoundException(String.format("User %s not found", usernameOrEmail));
        }

        User user = o.orElseThrow();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true, true,
                authorities);
    }
}
