package com.example.gameproject.userGame;


import com.example.gameproject.exception.GameNotFoundException;
import com.example.gameproject.exception.UserNotFoundException;
import com.example.gameproject.game.Game;
import com.example.gameproject.game.GameService;
import com.example.gameproject.user.User;
import com.example.gameproject.user.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userGame")
public class UserGameController {

    private final UserGameRepository repo;
    private final UserServer userServer;
    private final GameService gameService;

    @Autowired
    public UserGameController(UserGameRepository repo, UserServer userServer, GameService gameService) {
        this.repo = repo;
        this.userServer = userServer;
        this.gameService = gameService;
    }

    /**
     * @return all gameUser
     */
    @GetMapping
    List<UserGame> getUserGames(){
        return repo.findAll();
    }

    /**
     * @param userGameRequest
     * @throws UserNotFoundException
     * @throws GameNotFoundException
     * Explanation: make a relation between a user and a game
     */
    @PostMapping
    void addUserGame(@RequestBody UserGameRequest userGameRequest) throws UserNotFoundException, GameNotFoundException {
        User user = userServer.getUserById(userGameRequest.getUserId());
        Game game = gameService.getGameById(userGameRequest.getGameId());
        String status = userGameRequest.getStatus();
        UserGame userGame = new UserGame();
        userGame.setUser(user);
        userGame.setGame(game);
        userGame.setStatus(status);
        repo.save(userGame);
    }
}
