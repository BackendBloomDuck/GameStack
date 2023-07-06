package com.example.gameproject.user;


import com.example.gameproject.DTOs.MessageResponse;
import com.example.gameproject.exception.GameNotFoundException;
import com.example.gameproject.exception.UserNotFoundException;
import com.example.gameproject.user.userInfo.UserInfoUserDetails;
import com.example.gameproject.userGame.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService{
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ResponseEntity<User> addUser(User user) {
        Optional<User> found = userRepository.findByUsername( user.getUsername() );
        if ( found.isPresent() ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST )
                    .body( found.get() );
        }
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        userRepository.save( user );
        ResponseEntity response = new ResponseEntity( "new account has been created with this email: " + user.getEmail(), HttpStatus.CREATED );
        return response;
    }
    @Override
    public void updateUser(User newUser, int id) throws UserNotFoundException {
        Optional<User> found = userRepository.findById( id );
        if(found.isEmpty())
            throw new UserNotFoundException("The game is NOT exist");

        if(Objects.nonNull(newUser.getEmail()) && !"".equalsIgnoreCase(newUser.getEmail()))
            found.get().setEmail(newUser.getEmail());

        if(Objects.nonNull(newUser.getEmail()) && !"".equalsIgnoreCase(newUser.getEmail()))
            found.get().setEmail(newUser.getEmail());

        if(Objects.nonNull(newUser.getPassword()) && !"".equalsIgnoreCase(newUser.getPassword()))
            found.get().setPassword(newUser.getPassword());

        userRepository.save( found.get() );

    }

    @Override
    public User getUser(String username) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            return user.get();
        }
        throw new UserNotFoundException("user not found");
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        throw new UserNotFoundException("user not found");

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserGame> getFinishedGames(int id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get().getUserGames().stream()
                    .filter( userGame -> Objects.equals(userGame.getStatus(), "finished")).collect(Collectors.toList());
        throw new UserNotFoundException();
    }

    @Override
    public List<UserGame> getBacklogGames(int id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get().getUserGames().stream()
                    .filter( userGame -> Objects.equals(userGame.getStatus(), "backlog")).collect(Collectors.toList());
        throw new UserNotFoundException();
    }

    @Override
    public List<UserGame> getPlayingGames(int id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get().getUserGames().stream()
                    .filter( userGame -> Objects.equals(userGame.getStatus(), "playing")).collect(Collectors.toList());
        throw new UserNotFoundException();
    }


    public ResponseEntity<?> delete(Integer id) {
        Optional<User> user = userRepository.findById( id );
        if ( user.isPresent() ) {
            userRepository.deleteById( id );
            return ResponseEntity.status( HttpStatus.NO_CONTENT).body( "user has been deleted" );
        }
        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                .body( "user not found" );

    }
}

