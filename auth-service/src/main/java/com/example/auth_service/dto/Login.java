package com.example.auth_service.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String username;
    private String password;
}
