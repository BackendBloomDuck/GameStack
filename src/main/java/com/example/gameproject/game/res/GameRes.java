package com.example.gameproject.game.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRes {
    private int id;
    private String name;
    private List<String> developer;
    private List<String> genres;
    private List<String> paltforms;
    private Integer metacritic_score;
    private String release_date;
    private Integer play_time;


    public static List<String> fillter(String s){
        return Arrays.stream(s.split("\\|\\|")).map(String::strip).collect(Collectors.toList());
    }


}
