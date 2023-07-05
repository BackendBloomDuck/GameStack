package com.example.gameproject.user;

import com.example.gameproject.exception.UserNotFoundException;
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
}
