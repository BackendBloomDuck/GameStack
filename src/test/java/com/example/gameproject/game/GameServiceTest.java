package com.example.gameproject.game;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;



class GameServiceTest {

    @Autowired
    private GameService service;

    @MockBean
    private GameRepository repositoryl;

@BeforeEach
    void setUp(){
    Game game = Game.builder()
            .name("name")
            .metacritic_score(10)
            .release_date("10-10-2011")
            .play_time(10)
            .paltforms("platform1 || platform2")
            .developer("developer1 || developer2")
            .genres("genre1 || genre2")
            .build();
}

}