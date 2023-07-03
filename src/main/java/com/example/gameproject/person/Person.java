package com.example.gameproject.person;

import com.example.gameproject.game.Game;
import jakarta.persistence.*;
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
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String user_name;

    @Column(length = 60)
    private String password;

    @Column(unique = true)
    private String email;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_game",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "game_id",
                    referencedColumnName = "id"
            )
    )
    private List<Game> games;
}
