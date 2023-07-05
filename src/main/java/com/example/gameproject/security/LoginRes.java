package com.example.gameproject.security;


import com.example.gameproject.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {
    private User user;
    private String token;
}
