package com.team.management.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team.management.api.DTO.PostRequest;
import com.team.management.api.DTO.PostResponse;
import com.team.management.api.models.Post;
import com.team.management.api.models.Profile;
import com.team.management.api.models.UserModel;
import com.team.management.api.repository.PostRepository;
import com.team.management.api.repository.ProfileRepository;
import com.team.management.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public PostResponse createPost(PostRequest request, UserDetails userDetails) {
        Profile profile = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found")).getProfile();
        Post post = Post.builder()
                .content(request.getContent())
                .createTime(LocalDateTime.now())
                .upDateTime(LocalDateTime.now())
                .profile(profile)
                .build();
        postRepository.save(post);
        profile.getPosts().add(post);
        profileRepository.save(profile);
        return PostResponse.builder()
                .content(request.getContent())
                .createAt(post.getCreateTime())
                .build();
    }

    public PostResponse updatePost(Long postId, PostRequest request, UserDetails userDetails) {
        Post post = postRepository.findById(postId).orElseThrow();
        Profile profile = userRepository.findByUsername(userDetails.getUsername()).get().getProfile();
        post.setContent(request.getContent());
        post.setUpDateTime(LocalDateTime.now());
        postRepository.save(post);
        profile.getPosts().add(post);
        profileRepository.save(profile);
        return PostResponse.builder()
                .content(post.getContent())
                .updateAt(post.getUpDateTime())
                .build();
    }

    public List<PostResponse> userPosts(UserDetails userDetails) {
        UserModel user = userRepository.findByUsername(userDetails.getUsername()).get();
        List<Post> posts = postRepository.findAllByProfile(user.getProfile());
        return posts.stream().map(post -> PostResponse.builder()
                .content(post.getContent())
                .createAt(post.getCreateTime())
                .updateAt(post.getUpDateTime())
                .build()).collect(Collectors.toList());
    }
}
