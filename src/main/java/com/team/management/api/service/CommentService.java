package com.team.management.api.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.team.management.api.DTO.CommentRequest;
import com.team.management.api.DTO.CommentResponse;
import com.team.management.api.DTO.PaginationComments;
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

    public PaginationComments postComments(Long postId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Post post = postRepository.findById(postId).get();
        Page<CommentResponse> pageComments = commentRepository.findAllByPost(post, pageable)
                .map(comment -> CommentResponse.builder()
                        .content(comment.getContent())
                        .createAt(comment.getCreateTime())
                        .updateAt(comment.getUpdateTime())
                        .build());

        return PaginationComments.builder()
                .comments(pageComments.getContent())
                .pageNo(pageComments.getNumber())
                .pageSize(pageComments.getSize())
                .totalElements(pageComments.getTotalElements())
                .totalPages(pageComments.getTotalPages())
                .last(pageComments.isLast())
                .build();
    }

    public PaginationComments userComments(UserDetails userDetails, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Profile profile = profileRepository.findByAccount(userDetails);
        Page<CommentResponse> pageComments = commentRepository.findAllByProfile(profile, pageable)
                .map(comment -> CommentResponse.builder()
                        .content(comment.getContent())
                        .createAt(comment.getCreateTime())
                        .updateAt(comment.getUpdateTime())
                        .build());

        return PaginationComments.builder()
                .comments(pageComments.getContent())
                .pageNo(pageComments.getNumber())
                .pageSize(pageComments.getSize())
                .totalElements(pageComments.getTotalElements())
                .totalPages(pageComments.getTotalPages())
                .last(pageComments.isLast())
                .build();
    }

    public CommentResponse updateComment(Long commentId, CommentRequest request, UserDetails userDetails) {
        Profile profile = profileRepository.findByAccount(userDetails);
        Comment comment = commentRepository.findById(commentId).get();
        if (comment.getProfile().equals(profile)) {
            comment.setContent(request.getContent());
            comment.setUpdateTime(LocalDateTime.now());
            commentRepository.save(comment);
            return CommentResponse.builder()
                    .content(comment.getContent())
                    .createAt(comment.getCreateTime())
                    .updateAt(comment.getUpdateTime())
                    .build();
        }
        return CommentResponse.builder()
                .content("You Are Not Authorized to Modify This Comment!")
                .createAt(LocalDateTime.now())
                .build();
    }
}
