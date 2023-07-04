package com.example.gameproject.user;

import com.example.gameproject.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServerImp implements UserServer{

    @Autowired
    private UserRepository repo;

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = repo.findById(id);
        if(user.isPresent())
            return user.get();
        throw new UserNotFoundException("The user is NOT exist");

    }
}
