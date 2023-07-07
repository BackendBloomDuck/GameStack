package com.example.gameproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameProjectApplication {
    public String port = System.getenv("PORT");


    public static void main(String[] args) {
        SpringApplication.run(GameProjectApplication.class, args);
    }

}
