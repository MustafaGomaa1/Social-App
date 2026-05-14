package com.team.management.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.management.api.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
