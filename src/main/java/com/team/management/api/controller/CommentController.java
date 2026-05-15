package com.team.management.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team.management.api.DTO.CommentRequest;
import com.team.management.api.DTO.CommentResponse;
import com.team.management.api.DTO.PaginationComments;
import com.team.management.api.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/new")
    public ResponseEntity<CommentResponse> createComment(@PathVariable("postId") Long id,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(commentService.createComment(userDetails, request, id), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/")
    public ResponseEntity<PaginationComments> postComments(@PathVariable("postId") Long id,
            @RequestParam(name = "pageNo", defaultValue = "0", required = true) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = true) int pageSize) {
        return ResponseEntity.ok(commentService.postComments(id, pageNo, pageSize));
    }

    @GetMapping("/mine")
    public ResponseEntity<PaginationComments> userComments(@AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "pageNo", defaultValue = "0", required = true) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = true) int pageSize) {
        return ResponseEntity.ok(commentService.userComments(userDetails, pageNo, pageSize));
    }

    @PutMapping("/{commentId}/update")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("commentId") Long id,
            @RequestBody CommentRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(commentService.updateComment(id, request, userDetails));
    }
}
