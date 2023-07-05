package com.example.gameproject.user;


import com.example.gameproject.DTOs.MessageResponse;
import com.example.gameproject.exception.UserNotFoundException;
import com.example.gameproject.userGame.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public ResponseEntity<?> addUser(User user) {
        if ( userRepository.findByUsername( user.getUsername() ).isPresent() ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST).body( "user already created" );

        }
        else {
            user.setPassword( passwordEncoder.encode( user.getPassword() ) );
            userRepository.save( user );
            return ResponseEntity.status( HttpStatus.CREATED )
                    .body( new MessageResponse( "new user created" ) );
        }

    }

    @Override
    public ResponseEntity<?> updateUser(int id, User newUser) {
        Optional<User> optionalUser = userRepository.findById( id );
        if ( optionalUser.isPresent() ) {
            User user = optionalUser.get();
            user.setEmail( newUser.getEmail() );
            user.setPassword( newUser.getPassword() );
            user.setUsername( newUser.getUsername() );
            userRepository.save( user );
            return ResponseEntity.status( HttpStatus.OK )
                    .body( "user has been updated" );
        }
        return ResponseEntity.status( HttpStatus.NOT_FOUND )
                .body( "user not found" );
    }

    @Override
    public ResponseEntity<?> getUser(String username) {
        if (userRepository.findByUsername( username ).isPresent()){
            return ResponseEntity.status( HttpStatus.FOUND).body( userRepository.findByUsername( username ) );
        }
        return ResponseEntity.status( HttpStatus.NOT_FOUND).body( "user not found" );

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
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent())
//            return (List<UserGame>) user.get().getUserGames().stream()
//                    .map(userGame ->{
//                        if (Objects.equals(userGame.getStatus(), "finished")) {
//                            return userGame;
//                        }
//                        return false;
//                    });
        throw new UserNotFoundException();
    }

    @Override
    public List<UserGame> getBacklogGames(int id) throws UserNotFoundException {
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent())
//            return (List<UserGame>) user.get().getUserGames().stream()
//                    .map(userGame ->{
//                        if (Objects.equals(userGame.getStatus(), "backlog")) {
//                            return userGame;
//                        }
//                        return false;
//                    });

        throw new UserNotFoundException();
    }

    @Override
    public List<UserGame> getPlayingGames(int id) throws UserNotFoundException {
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent())
//            return (List<UserGame>) user.get().getUserGames().stream()
//                    .map(userGame ->{
//                        if (Objects.equals(userGame.getStatus(), "playing")) {
//                            return userGame;
//                        }
//                        return false;
//                    });
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

