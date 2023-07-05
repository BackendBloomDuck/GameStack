package com.example.gameproject.game.res;


import com.example.gameproject.game.Game;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GamePagingRes {
    private int currentPage;
    private int totalPages;
    private int pageSize;
    private Long totalElements;
    private List<GameRes> gameRes;
}
