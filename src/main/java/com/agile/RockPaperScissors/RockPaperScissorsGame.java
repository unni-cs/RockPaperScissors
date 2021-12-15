package com.agile.RockPaperScissors;

public class RockPaperScissorsGame {
    public Player PlayerOne;
    public Player PlayerTwo;
    private GameStatus Status;
    private int CurrentRound = 0;
    protected int MaxRounds = 3;

    private static RockPaperScissorsRules rules  = new RockPaperScissorsRules();
    private static GameBoardStatusUpdateService gameBoardStatusUpdateService = new GameBoardStatusUpdateService();

    public RockPaperScissorsGame() {
        Status = new GameStatus();
    }

    public GameStatus Start()
    {        
        CurrentRound = 0;
        Status = new GameStatus();
        return Status;
    }

    public GameStatus Play() throws Exception{

        CurrentRound++;

        if(CurrentRound > MaxRounds){
            throw new Exception("Reached the max attempts!");
        }

        String roundWinner = rules.Execute(PlayerOne.Selection, PlayerTwo.Selection);

        Status = gameBoardStatusUpdateService.updateStatus(PlayerOne, PlayerTwo, roundWinner);

        Status.CurrentRound = CurrentRound;

        return Status;
    }

    public void Stop(){
        gameBoardStatusUpdateService.updateLeaderStatus(PlayerOne);
        gameBoardStatusUpdateService.updateLeaderStatus(PlayerTwo);
        PlayerOne.LeaderBoard.CurrentGame = new GameStatus();
        PlayerTwo.LeaderBoard.CurrentGame = new GameStatus();
    }
}
