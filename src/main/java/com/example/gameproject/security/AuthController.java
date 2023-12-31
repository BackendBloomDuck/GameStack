package com.example.gameproject.security;

import com.example.gameproject.DTOs.AuthRequest;
import com.example.gameproject.exception.AuthException;
import com.example.gameproject.exception.UserNotFoundException;
import com.example.gameproject.exception.UsernameFoundException;
import com.example.gameproject.user.User;
import com.example.gameproject.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/")
public class AuthController {


    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("login")
    public ResponseEntity<LoginRes> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws UserNotFoundException, AuthException {
        return getStringResponseEntity(authRequest);
    }

    @PostMapping("user/register")

    public ResponseEntity<User> addNewUser(@RequestBody User user) throws UsernameFoundException {
            user.setRoles("USER");
        return userService.addUser(user);

    }

    public ResponseEntity<LoginRes> getStringResponseEntity(@RequestBody AuthRequest authRequest)
            throws UserNotFoundException, AuthException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                        authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());

            User user = userService.getUser(authRequest.getUsername());

            return ResponseEntity.status(HttpStatus.OK).body(
                    new LoginRes(user.getUsername(),
                            user.getEmail(),
                            user.getName(),
                            user.getRoles(),
                            token));

        }
        else throw new AuthException();

    }
}
