package com.example.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDetails {
    private Long id;
    private String role;
}
