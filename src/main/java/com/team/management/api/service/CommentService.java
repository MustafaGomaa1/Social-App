package com.team.management.api.service;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.team.management.api.DTO.CommentRequest;
import com.team.management.api.DTO.CommentResponse;
import com.team.management.api.models.Comment;
import com.team.management.api.models.Post;
import com.team.management.api.models.Profile;
import com.team.management.api.repository.CommentRepository;
import com.team.management.api.repository.PostRepository;
import com.team.management.api.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;

    public CommentResponse createComment(UserDetails userDetails, CommentRequest request, Long postId) {
        Profile profile = profileRepository.findByAccount(userDetails);
        Post post = postRepository.findById(postId).get();
        Comment comment = Comment.builder()
                .content(request.getContent())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .post(post)
                .profile(profile)
                .build();
        commentRepository.save(comment);
        post.getComments().add(comment);
        profile.getComments().add(comment);
        postRepository.save(post);
        profileRepository.save(profile);
        return CommentResponse.builder()
                .content(request.getContent())
                .createAt(comment.getCreateTime())
                .updateAt(comment.getUpdateTime())
                .build();
    }
}
