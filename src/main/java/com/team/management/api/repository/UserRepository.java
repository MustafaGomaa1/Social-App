package com.team.management.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.management.api.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUsername(String username);
}
