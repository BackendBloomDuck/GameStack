package com.example.gameproject.user;


import com.example.gameproject.exception.UserNotFoundException;
import com.example.gameproject.responses.MessageRes;
import com.example.gameproject.security.JwtService;
import com.example.gameproject.userGame.UserGame;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/all")
    public List<User> getAllUser(HttpRequest request) {
        return userService.getAllUsers();
    }

    @GetMapping
    public ResponseEntity<User> getUserByUsername(@RequestHeader String Authorization) throws UserNotFoundException {
        String username = jwtService.extractUsername(Authorization);
        User user = userService.getUser(username);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @PutMapping
    ResponseEntity<MessageRes> updateUser(@RequestBody User user, @PathVariable int id) throws UserNotFoundException {
        userService.updateUser(user, id);
        return new ResponseEntity<>(new MessageRes("The user is updated"), HttpStatus.OK);
    }


    @GetMapping("/finished")
    public List<UserGame> getFinishedGames(@RequestHeader String Authorization) throws UserNotFoundException {
        String username = jwtService.extractUsername(Authorization);
        User user = userService.getUser(username);
        return userService.getFinishedGames(user.getId());
    }

    @GetMapping("/backlog")
    public List<UserGame> getBacklogGames(@PathVariable int id) throws UserNotFoundException {
        return userService.getBacklogGames(id);
    }

    @GetMapping("/playing")
    public List<UserGame> getPlayingGames(@PathVariable int id) throws UserNotFoundException {
        return userService.getPlayingGames(id);
    }



}
