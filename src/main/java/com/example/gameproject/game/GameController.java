package com.example.gameproject.game;

import com.example.gameproject.exception.GameNotFoundException;
import com.example.gameproject.game.res.GamePagingRes;
import com.example.gameproject.game.res.GameRes;
import com.example.gameproject.responses.MessageRes;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Game", description = "Game management APIs")
@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    /**
     *    Value: the value is come from resources application.yml
     *    Represent: the size of a page.
     *    Page: the number of element per page
     */
    @Value("${setting.pageSize}")
    private Integer pageSize;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    /**
     *
     * @param name
     * @param pageNumber
     * @return list of games and that are warped by GamePagingRes
     */
    @GetMapping
    public GamePagingRes getGameByNameIgnoreCaseWithPaging(@RequestParam String name, @RequestParam int pageNumber){
        Pageable request = PageRequest.of(pageNumber, pageSize);
        List<Object> objects = gameService.findByNameIgnoreCaseWithPaging(name, request);

        List<GameRes> gameRes = ((List<Game>) objects.get(2)).stream().map(game -> new GameRes(
                        game.getId(),
                        game.getName(),
                        GameRes.fillter(game.getDeveloper()),
                        GameRes.fillter(game.getGenres()),
                        GameRes.fillter(game.getPaltforms()),
                        game.getMetacritic_score(),
                        game.getRelease_date(),
                        game.getPlay_time()

        )).toList();

        return new GamePagingRes(pageNumber, (int) objects.get(0), pageSize, (long) objects.get(1), gameRes);
    }

    /**
     *
     * @param id
     * @return Game
     * @throws GameNotFoundException
     */
    @GetMapping("/{id}")
    public GameRes getGameById(@PathVariable int id) throws GameNotFoundException{
        Game game = gameService.getGameById(id);
        return new GameRes(
                game.getId(),
                game.getName(),
                GameRes.fillter(game.getDeveloper()),
                GameRes.fillter(game.getGenres()),
                GameRes.fillter(game.getPaltforms()),
                game.getMetacritic_score(),
                game.getRelease_date(),
                game.getPlay_time()
        );
    }

    /**
     * @param game
     * @return MessageRes that has a message string and status
     * @throws GameNotFoundException
     */
    @PostMapping
    ResponseEntity<MessageRes> addGame(@RequestBody Game game) throws GameNotFoundException {
        gameService.addGame(game);
        return new ResponseEntity<>(new MessageRes("The game is added"), HttpStatus.CREATED);
    }

    /**
     * @param game
     * @param id
     * @return MessageRes that has a message string and status
     * @throws GameNotFoundException
     */
    @PutMapping("/{id}")
    ResponseEntity<MessageRes> updateGame(@RequestBody Game game, @PathVariable int id) throws GameNotFoundException {
        gameService.updateGame(game, id);
        return new ResponseEntity<>(new MessageRes("The game is updated"), HttpStatus.OK);
    }
}
