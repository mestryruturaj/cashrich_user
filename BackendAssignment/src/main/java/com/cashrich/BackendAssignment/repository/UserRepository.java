package com.cashrich.BackendAssignment.repository;

import com.cashrich.BackendAssignment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);
}
