package com.agile.RockPaperScissors;


public class Player {

    public Player() {
        LeaderBoard = new GameBoard();
    }

   public String Name;
   public Options Selection;
   public GameBoard LeaderBoard;
}
