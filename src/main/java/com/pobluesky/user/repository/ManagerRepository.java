package com.pobluesky.user.repository;

import com.pobluesky.global.security.UserRole;
import com.pobluesky.user.entity.Manager;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Manager> findByRole(UserRole role);
}
