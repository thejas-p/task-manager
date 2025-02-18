package com.tp.taskmanager.task_manager.security;

import com.tp.taskmanager.task_manager.model.User;
import com.tp.taskmanager.task_manager.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        //String roleName = appUser.getRole().name().replace("ROLE_", "")
        return org.springframework.security.core.userdetails.User.withUsername(appUser.getUsername())
                .password(appUser.getPassword())
               // .roles(appUser.getRole().name().replace("ROLE_", ""))
                .authorities(String.valueOf(appUser.getRole()))
                .build();
    }
}