package com.team.management.api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team.management.api.DTO.AuthRequest;
import com.team.management.api.DTO.AuthResponse;
import com.team.management.api.DTO.RegisterRequest;
import com.team.management.api.models.Role;
import com.team.management.api.models.UserModel;
import com.team.management.api.repository.UserRepository;
import com.team.management.api.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public AuthResponse register(RegisterRequest request) {
        UserModel user = new UserModel();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Role.USER);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        log.info(jwtToken);
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserModel user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
