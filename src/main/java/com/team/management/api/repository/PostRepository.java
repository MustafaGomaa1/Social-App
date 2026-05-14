package com.team.management.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.management.api.models.Post;
import com.team.management.api.models.Profile;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByProfile(Profile profile);
}
