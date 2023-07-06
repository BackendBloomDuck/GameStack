package com.example.gameproject.game.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameReq {
    private String name;
    private List<String> developer;
    private List<String> genres;
    private List<String> paltforms;
    private Integer metacritic_score;
    private String release_date;
    private Integer play_time;
}
