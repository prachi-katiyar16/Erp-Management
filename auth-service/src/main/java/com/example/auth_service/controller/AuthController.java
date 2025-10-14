package com.example.auth_service.controller;

import com.example.auth_service.dto.Login;
import com.example.auth_service.dto.UserAuthDetails;
import com.example.auth_service.entity.AppUser;
import com.example.auth_service.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody AppUser user){
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public String getToken(@RequestBody Login authRequest) {
        System.out.println(authRequest);
      logger.info("username {}",authRequest.getUsername());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authenticate);
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public UserAuthDetails validateToken(@RequestParam("token") String token){
        System.out.println("Reached");
        return service.validateToken(token);
    }
}
