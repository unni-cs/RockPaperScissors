package com.agile.RockPaperScissors;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    public GameBoard() {
        LeaderStatus = new ArrayList<GameStatus>();
        CurrentGame = new GameStatus();
    }

    public List<GameStatus> LeaderStatus;
    public GameStatus CurrentGame;
}
