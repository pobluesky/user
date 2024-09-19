package com.pobluesky.user.service;

import com.pobluesky.user.entity.User;
import com.pobluesky.user.repository.CustomerRepository;
import com.pobluesky.user.repository.ManagerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    private final ManagerRepository managerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return customerRepository.findByEmail(email)
            .map(this::createUserDetails)
            .or(() -> managerRepository.findByEmail(email)
                .map(this::createUserDetails))
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(User user) {

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            passwordEncoder.encode(user.getPassword()),
            user.getAuthorities()
        );
    }
}
