package com.team.management.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.management.api.DTO.PostRequest;
import com.team.management.api.DTO.PostResponse;
import com.team.management.api.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/new")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(postService.createPost(request, userDetails), HttpStatus.CREATED);
    }

    @PutMapping("update/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(postService.updatePost(id, request, userDetails));
    }

    @GetMapping("/")
    public ResponseEntity<List<PostResponse>> userPosts(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(postService.userPosts(userDetails));
    }

}
