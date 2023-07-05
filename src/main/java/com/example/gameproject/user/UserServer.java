package com.example.gameproject.user;

import com.example.gameproject.exception.UserNotFoundException;

public interface UserServer {
    User getUserById(int id) throws UserNotFoundException;
}
