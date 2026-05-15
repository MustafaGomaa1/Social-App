package com.team.management.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.team.management.api.models.Comment;
import com.team.management.api.models.Post;
import com.team.management.api.models.Profile;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPost(Post post, Pageable pageable);

    Page<Comment> findAllByProfile(Profile profile, Pageable pageable);
}
