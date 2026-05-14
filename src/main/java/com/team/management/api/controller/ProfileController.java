package com.team.management.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.management.api.DTO.ProfileRequest;
import com.team.management.api.DTO.ProfileResponse;
import com.team.management.api.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/new")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ProfileResponse> createProfile(@RequestBody ProfileRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(profileService.createProfile(request, userDetails.getUsername()),
                HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ProfileResponse> updateProfile(@RequestBody ProfileRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(profileService.updateProfile(request, userDetails));
    }

    @GetMapping("/my-profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ProfileResponse> myProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(profileService.showProfileData(userDetails));
    }

}
