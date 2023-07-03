package com.example.gameproject.csv;

import com.example.gameproject.game.Game;
import com.example.gameproject.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    private final GameRepository gameRepository;


    @Autowired
    public CSVService(GameRepository repository) {
        this.gameRepository = repository;
    }


    public void save(MultipartFile file) {
        try {
            List<Game> games = CSVHelper.csvToTutorials(file.getInputStream());
            gameRepository.saveAll( games );
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
