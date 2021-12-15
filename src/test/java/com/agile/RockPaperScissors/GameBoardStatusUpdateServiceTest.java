package com.agile.RockPaperScissors;

import junit.framework.TestCase;

public class GameBoardStatusUpdateServiceTest extends TestCase
{    
    GameBoardStatusUpdateService gameBoardStatusUpdateService = new GameBoardStatusUpdateService();
    Player playerOne;
    Player playerTwo;

    public GameBoardStatusUpdateServiceTest() {
        playerOne = new Player();
        playerTwo = new Player();
        playerOne.Name = "P1";
        playerTwo.Name = "P2";
    }

    public void test_WhenGivenProperParameters_ShouldUpdateGameBoardStatus()
    {
        //Given        
        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Scissors; 

        //When
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "P1");

        //Then
        assertNotNull(playerOne.LeaderBoard.CurrentGame);
        assertNotNull(playerTwo.LeaderBoard.CurrentGame);
        assertEquals("P1", playerOne.LeaderBoard.CurrentGame.Winner);
        assertEquals("P1", playerTwo.LeaderBoard.CurrentGame.Winner);
    }

    public void test_WhenGameTie_ShouldUpdateTheResultAsTie()
    {
        //Given
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "TIE");
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "TIE");

        //When
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "P1");

        //Then
        assertNotNull(playerOne.LeaderBoard.CurrentGame.Winner);
        assertEquals("TIE", playerOne.LeaderBoard.CurrentGame.Winner);
    }

    public void test_WhenPlayerOneWins_ShouldUpdateP1AsWinner()
    {
        //Given
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "P1");
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "TIE");

        //When
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "P1");

        //Then
        assertNotNull(playerOne.LeaderBoard.CurrentGame.Winner);
        assertEquals("P1", playerOne.LeaderBoard.CurrentGame.Winner);
    }

    public void test_WhenPlayerTwoWins_ShouldUpdateP2AsWinner()

    {
        //Given
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "P1");
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "P2");

        //When
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "P2");

        //Then
        assertNotNull(playerTwo.LeaderBoard.CurrentGame.Winner);
        assertEquals("P2", playerTwo.LeaderBoard.CurrentGame.Winner);
    }

    public void test_WhenGameIsComplete_ShouldUpdateLeaderBoardStatus(){
        //Given 
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "P1");
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "P2");
        gameBoardStatusUpdateService.updateStatus(playerOne, playerTwo, "P2");
        
        //When Game1 is complete
        gameBoardStatusUpdateService.updateLeaderStatus(playerOne);
        gameBoardStatusUpdateService.updateLeaderStatus(playerTwo);

        //Then
        assertNotNull(playerTwo.LeaderBoard.CurrentGame.Winner);
        assertEquals("P2", playerTwo.LeaderBoard.CurrentGame.Winner);
        assertNotNull(playerOne.LeaderBoard.LeaderStatus);
        assertNotNull(playerTwo.LeaderBoard.LeaderStatus);
        assertEquals(1, playerOne.LeaderBoard.LeaderStatus.size());
        assertEquals(1, playerTwo.LeaderBoard.LeaderStatus.size());
    }
}
