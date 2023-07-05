package com.example.gameproject.user;


import com.example.gameproject.exception.UserNotFoundException;
import com.example.gameproject.userGame.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserName(@PathVariable int id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/finished")
    public List<UserGame> getFinishedGames(@PathVariable int id) throws UserNotFoundException {
        return userService.getFinishedGames(id);
    }

    @GetMapping("/{id}/backlog")
    public List<UserGame> getBacklogGames(@PathVariable int id) throws UserNotFoundException {
        return userService.getBacklogGames(id);
    }

    @GetMapping("/{id}/playing")
    public List<UserGame> getPlayingGames(@PathVariable int id) throws UserNotFoundException {
        return userService.getPlayingGames(id);
    }
}
