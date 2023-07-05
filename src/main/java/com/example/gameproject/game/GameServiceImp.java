package com.example.gameproject.game;

import com.example.gameproject.exception.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameServiceImp implements GameService {

    @Autowired
    private GameRepository repo;


    /**
     * @param name
     * @param pageable
     * @return list of total pages, total elements and the page content
     */
    @Override
    public List<Object> findByNameIgnoreCaseWithPaging(String name, Pageable pageable) {
        Page<Game> page = repo.findByNameContainingIgnoreCase(name, pageable);
        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        return List.of(totalPages, totalElements, page.getContent());
    }

    /**
     * @param id
     * @return game
     * @throws GameNotFoundException
     */
    @Override
    public Game getGameById(int id) throws GameNotFoundException {
        Optional<Game> game = repo.findById(id);
        if(game.isPresent())
            return game.get();
        throw new GameNotFoundException("The game is NOT exist");
    }

    /**
     * @param game
     * @throws GameNotFoundException
     */
    @Override
    public void addGame(Game game) throws GameNotFoundException {
        Optional<Game> found = repo.findById(game.getId());
        if(found.isPresent())
            throw new GameNotFoundException("The game is NOT exist");

        repo.save(game);
    }

    /**
     * @param game
     * @param id
     * @throws GameNotFoundException
     *
     * Explanation: update the exising game
     *  if anybody value brought that has a value (not null or no value)
     */
    @Override
    public void updateGame(Game game, int id) throws GameNotFoundException {
        Optional<Game> found = repo.findById(id);
        if(found.isEmpty())
            throw new GameNotFoundException("The game is NOT exist");

        if(Objects.nonNull(game.getName()) && !"".equalsIgnoreCase(game.getName()))
            found.get().setName(game.getName());

        if(Objects.nonNull(game.getDeveloper()) && !"".equalsIgnoreCase(game.getDeveloper()))
            found.get().setDeveloper(game.getDeveloper());

        if(Objects.nonNull(game.getGenres()) && !"".equalsIgnoreCase(game.getGenres()))
            found.get().setGenres(game.getGenres());

        if(Objects.nonNull(game.getPaltforms()) && !"".equalsIgnoreCase(game.getPaltforms()))
            found.get().setPaltforms(game.getPaltforms());

        if(Objects.nonNull(game.getMetacritic_score()) && game.getMetacritic_score() >= 0)
            found.get().setMetacritic_score(game.getMetacritic_score());

        if(Objects.nonNull(game.getRelease_date()) && !"".equalsIgnoreCase(game.getRelease_date()))
            found.get().setRelease_date(game.getRelease_date());

        if(Objects.nonNull(game.getPlay_time()) && game.getPlay_time() >= 0)
            found.get().setPlay_time(game.getPlay_time());

        repo.save(found.get());

    }
}
