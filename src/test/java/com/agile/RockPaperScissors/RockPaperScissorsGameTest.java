package com.agile.RockPaperScissors;

import static org.junit.jupiter.api.Assertions.assertThrows;

import junit.framework.TestCase;

public class RockPaperScissorsGameTest extends TestCase
{
    RockPaperScissorsGame game = new RockPaperScissorsGame();    
    GameBoardStatusUpdateService gameBoardStatusUpdateService = new GameBoardStatusUpdateService();

    Player playerOne = new Player();
    Player playerTwo = new Player();

    public RockPaperScissorsGameTest() 
    {
        playerOne.Name = "P1";
        playerTwo.Name = "P2";
        game.PlayerOne = playerOne;
        game.PlayerTwo = playerTwo;
    }
    
    public void test_WhenStartingTheGame_ShouldReturnEmptyGameStatus()
    {
        //Given
        //Two players registered for the game

        //When
        GameStatus actual = game.Start();

        //Then
        assertNotNull(actual);
        assertEquals(0, actual.Rounds.size());
        assertNull(actual.Winner);
        assertEquals(0, actual.CurrentRound);
    }
    
    public void test_WhenPlayingTheGame_ShouldReturnGameStatus() throws Exception
    {
        //Given        
        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Rock;        

        int expectedRound = 1;
        String expectedWinner = "TIE";
        //When
        GameStatus actual = game.Play();

        //Then
        assertNotNull(actual);
        assertEquals(expectedRound, actual.Rounds.size());
        assertEquals(expectedRound, actual.CurrentRound);
        assertEquals(expectedWinner, actual.Winner);
    }

    public void test_WhenPlayingTheGameSecondRound_ShouldReturnGameStatus() throws Exception
    {
        //Given        
        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Rock;    

        game.Play(); //Round1

        int expectedRound = 2;
        String expectedWinner = "P1";

        //When
        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Scissors; 
        GameStatus actual = game.Play();

        //Then
        assertNotNull(actual);
        assertEquals(expectedRound, actual.Rounds.size());
        assertEquals(expectedWinner, actual.Winner);
    }

    public void test_WhenPlayingTheWholeGame_ShouldReturnTheWinner() throws Exception
    {
        //Given        
        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Rock; 
        game.Play(); //Round1

        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Scissors; 
        game.Play(); //Round2

        playerOne.Selection = Options.Paper;
        playerTwo.Selection = Options.Scissors;
        GameStatus actual = game.Play(); //Round3
        
        //When
        game.Stop();
        
        //Then
        assertNotNull(actual);
        assertEquals(3, actual.Rounds.size());
        assertEquals("TIE", actual.Winner);
        assertEquals(0, playerOne.LeaderBoard.CurrentGame.Rounds.size());
        assertEquals(0, playerTwo.LeaderBoard.CurrentGame.Rounds.size());
    }

    public void test_WhenExceedingTheRounds_ShouldThrowException() throws Exception{
        //Given
        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Rock; 
        game.Play(); //Round1

        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Scissors;
        game.Play(); //Round2

        playerOne.Selection = Options.Paper;
        playerTwo.Selection = Options.Scissors;
        game.Play(); //Round3

        playerOne.Selection = Options.Paper;
        playerTwo.Selection = Options.Scissors;  

        Exception exception = assertThrows(Exception.class, ()->  { game.Play();}); //Round4
        
        assertNotNull(exception);
        assertEquals("Reached the max attempts!", exception.getMessage());
    }
}
