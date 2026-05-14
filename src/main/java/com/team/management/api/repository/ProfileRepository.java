package com.team.management.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.management.api.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
