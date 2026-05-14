package com.team.management.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.management.api.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
