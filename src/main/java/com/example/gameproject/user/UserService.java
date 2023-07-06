package com.example.gameproject.user;

import com.example.gameproject.exception.UserNotFoundException;
import com.example.gameproject.exception.UsernameFoundException;
import com.example.gameproject.userGame.UserGame;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService{

    ResponseEntity<User> addUser(User user) throws UsernameFoundException;

     void updateUser(User newUser, int id) throws UserNotFoundException;

    User getUser(String username) throws UserNotFoundException;
    User getUserById(int userId) throws UserNotFoundException;
    List<User> getAllUsers();

    List<UserGame> getFinishedGames(int id) throws UserNotFoundException;

    List<UserGame> getBacklogGames(int id) throws UserNotFoundException;

    List<UserGame> getPlayingGames(int id) throws UserNotFoundException;
}
