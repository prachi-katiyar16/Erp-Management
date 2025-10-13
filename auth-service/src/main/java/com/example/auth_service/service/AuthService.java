package com.example.auth_service.service;

import com.example.auth_service.dto.UserAuthDetails;
import com.example.auth_service.entity.AppUser;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class    AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String saveUser(AppUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "Added User";
    }

    public String generateToken(Authentication authentication) {
        return jwtUtil.generateToken(authentication);
    }


    public UserAuthDetails validateToken(String token){
        return jwtUtil.validateAndExtractDetails(token);
    }
}
