package com.springBoot.restfulApiBlogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<com.springBoot.restfulApiBlogApp.entity.User, Long> {
    Optional<com.springBoot.restfulApiBlogApp.entity.User> findByEmail(String email);
    Optional<com.springBoot.restfulApiBlogApp.entity.User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
