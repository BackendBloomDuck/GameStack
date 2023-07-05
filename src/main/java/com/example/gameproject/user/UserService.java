package com.example.gameproject.user;

import com.example.gameproject.exception.UserNotFoundException;
import com.example.gameproject.userGame.UserGame;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService{

    //     adding a new user
    public ResponseEntity<?> addUser(User user);

    // update user
    public ResponseEntity<?> updateUser(int id, User newUser);

    public ResponseEntity<?> getUser(String username);

    User getUserById(int userId) throws UserNotFoundException;

    List<User> getAllUsers();

    List<UserGame> getFinishedGames(int id) throws UserNotFoundException;
}
