package com.example.gameproject.userGame;

import lombok.Data;

@Data
public class UserGameRequest {
    private  String status;
    private  int userId;
    private  int gameId;
}
