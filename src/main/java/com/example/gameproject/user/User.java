package com.example.gameproject.user;


import com.example.gameproject.userGame.UserGame;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userTable")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @Column( nullable = false, unique = true )
    private String username;
    private  String name;
    private String email;
    @Column( nullable = false )
    private String password;
    @Column( nullable = false )
    private String roles;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<UserGame> userGames;
}
