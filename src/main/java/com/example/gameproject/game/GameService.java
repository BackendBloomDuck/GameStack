package com.example.gameproject.game;

import com.example.gameproject.exception.GameNotFoundException;
import com.example.gameproject.game.req.GameReq;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameService {

    List<Object> findByNameIgnoreCaseWithPaging(String name, Pageable pageable);

    Game getGameById(int id) throws GameNotFoundException;

    void addGame(GameReq game) throws GameNotFoundException;

    void updateGame(Game game, int id) throws GameNotFoundException;
     List<Object> findAllWithoutName( Pageable pageable);
}
