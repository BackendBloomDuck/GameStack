package com.example.gameproject.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {

    private String username;
    private String email;
    private String name;
    private String roles;
    private String token;
}
