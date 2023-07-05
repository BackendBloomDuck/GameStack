package com.example.gameproject.game;

import com.example.gameproject.exception.GameNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameService {

    List<Object> findByNameIgnoreCaseWithPaging(String name, Pageable pageable);

    Game getGameById(int id) throws GameNotFoundException;

    void addGame(Game game) throws GameNotFoundException;

    void updateGame(Game game, int id) throws GameNotFoundException;
}
