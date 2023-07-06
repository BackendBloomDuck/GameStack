package com.example.gameproject.user;


import com.example.gameproject.exception.UserNotFoundException;
import com.example.gameproject.responses.MessageRes;
import com.example.gameproject.security.JwtService;
import com.example.gameproject.userGame.UserGame;
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
    public List<User> getAllUser(@RequestHeader String Authorization) {
       String username = jwtService.extractUsername( Authorization );
        return userService.getAllUsers();
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


    @PutMapping("/{id}")
    ResponseEntity<MessageRes> updateUser(@RequestBody User user, @PathVariable int id) throws UserNotFoundException {
        userService.updateUser(user, id);
        return new ResponseEntity<>(new MessageRes("The user is updated"), HttpStatus.OK);
    }
}
