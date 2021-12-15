package com.agile.RockPaperScissors;

public class GameBoardStatusUpdateService 
{   
    public GameStatus updateStatus(Player playerOne, Player playerTwo, String roundWinner)
    {
        GameStatus  currentStatus = playerOne.LeaderBoard.CurrentGame;

        int round = currentStatus.Rounds.size() == 0 ? 1 : currentStatus.Rounds.size();

        String roundWinnerName = getWinnerName(playerOne, playerTwo, roundWinner);

        GameRound currentRoundStatus = new GameRound() {{ Round = round; Winner = roundWinnerName; }};        

        playerOne.LeaderBoard.CurrentGame.Rounds.add(currentRoundStatus);
        playerTwo.LeaderBoard.CurrentGame.Rounds.add(currentRoundStatus);

        String gameWinner = getGameWinner(playerOne, playerTwo);
        playerOne.LeaderBoard.CurrentGame.Winner = gameWinner;        
        playerTwo.LeaderBoard.CurrentGame.Winner = gameWinner;
        
        return playerOne.LeaderBoard.CurrentGame;
    }

    private String getWinnerName(Player playerOne, Player playerTwo, String roundWinner){
        if(roundWinner == "P1"){
            return playerOne.Name;
        }
        else if(roundWinner == "P2"){
            return playerTwo.Name;
        }
        else{
            return roundWinner;
        }
    }

    private String getGameWinner(Player playerOne, Player playerTwo)
    {
        int tie = 0;
        int p1 = 0;
        int p2 = 0;

        for (GameRound round : playerOne.LeaderBoard.CurrentGame.Rounds) 
        {
            if(round.Winner == "TIE"){
                tie++;
            }
            else if( round.Winner == playerOne.Name){
                p1++;
            }
            else if( round.Winner == playerTwo.Name){
                p2++;
            }
        }
        

        if(tie> 1 || p1 == p2){
            return "TIE";
        }
        else if(p1 > p2){
            return playerOne.Name;
        }
        else{
            return playerTwo.Name;
        }
    }

    public void updateLeaderStatus(Player player){        
        player.LeaderBoard.LeaderStatus.add(player.LeaderBoard.CurrentGame);
    }
}
