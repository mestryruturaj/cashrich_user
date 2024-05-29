package com.cashrich.BackendAssignment.repository;

import com.cashrich.BackendAssignment.Entity.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
}
