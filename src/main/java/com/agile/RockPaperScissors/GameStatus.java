package com.agile.RockPaperScissors;

import java.util.ArrayList;
import java.util.List;

public class GameStatus  {

    public GameStatus() {
        Rounds = new ArrayList<GameRound>();
    }

    public List<GameRound> Rounds;
    public String Winner;
    public int CurrentRound;
}
