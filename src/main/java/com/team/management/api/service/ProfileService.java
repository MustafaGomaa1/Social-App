package com.team.management.api.service;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team.management.api.DTO.ProfileRequest;
import com.team.management.api.DTO.ProfileResponse;
import com.team.management.api.models.Profile;
import com.team.management.api.models.UserModel;
import com.team.management.api.repository.ProfileRepository;
import com.team.management.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    // private JwtService jwtService;

    public ProfileResponse createProfile(ProfileRequest request, String userName) {
        UserModel user = userRepository.findByUsername(userName).get();
        Profile profile = Profile.builder()
                .account(user)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .joinAt(LocalDateTime.now())
                .photoUrl(request.getPhotoUrl())
                .build();
        profileRepository.save(profile);
        user.setProfile(profile);
        userRepository.save(user);
        return ProfileResponse.builder().fullname(profile.getFirstname() + " " + profile.getLastname()).build();
    }

    public ProfileResponse updateProfile(ProfileRequest request, UserDetails userDetails) {
        UserModel user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        Profile profile = user.getProfile();
        profile.setFirstname(request.getFirstname());
        profile.setLastname(request.getLastname());
        profile.setPhotoUrl(request.getPhotoUrl());
        user.setProfile(profile);
        userRepository.save(user);
        profileRepository.save(profile);
        return ProfileResponse.builder().fullname(profile.getFirstname() + " " + profile.getLastname()).build();
    }

    public ProfileResponse showProfileData(UserDetails userDetails) {
        UserModel user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return ProfileResponse.builder()
                .fullname(user.getProfile().getFirstname() + " " + user.getProfile().getLastname())
                .photoUrl(user.getProfile().getPhotoUrl())
                .build();
    }

}
