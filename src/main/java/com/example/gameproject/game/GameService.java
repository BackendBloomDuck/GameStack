package com.example.gameproject.game;

import com.example.gameproject.exception.GameNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameService {

    List<Object> findByNameIgnoreCaseWithPaging(String name, Pageable pageable);

    Game getGameById(Long id) throws GameNotFoundException;

    void addGame(Game game) throws GameNotFoundException;

    void updateGame(Game game, Long id) throws GameNotFoundException;
}
