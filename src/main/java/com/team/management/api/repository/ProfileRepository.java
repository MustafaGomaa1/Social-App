package com.team.management.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.team.management.api.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByAccount(UserDetails user);

}
