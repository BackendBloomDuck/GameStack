package com.example.gameproject.userGame;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class GameUserID implements Serializable {
    @Column(name = "game_id")
    int gameId;

    @Column(name = "user_id")
    int userId;
}
