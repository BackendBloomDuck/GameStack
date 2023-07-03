package com.example.gameproject.game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String developer;
    private String genres;
    private String paltforms;
    private Integer metacritic_score;
    private String release_date;
    private Integer play_time;


    public Game(String name, int metacritic, String released, int playtime, String platforms, String developers, String genres) {
        this.name = name;
        this.metacritic_score = metacritic;
        this.release_date = released;
        this.play_time = playtime;
        this.paltforms = platforms;
        this.developer = developers;
        this.genres = genres;
    }
}
