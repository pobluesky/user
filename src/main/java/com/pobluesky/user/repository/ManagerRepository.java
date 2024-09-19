package com.pobluesky.user.repository;

import com.pobluesky.user.entity.Manager;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByEmail(String email);

    boolean existsByEmail(String email);
}
