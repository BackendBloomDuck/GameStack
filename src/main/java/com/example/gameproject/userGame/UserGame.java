package com.example.gameproject.userGame;


import com.example.gameproject.game.Game;
import com.example.gameproject.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class UserGame {

    @EmbeddedId
    GameUserID id;

    private String status;

    @JsonIgnore
    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

}
