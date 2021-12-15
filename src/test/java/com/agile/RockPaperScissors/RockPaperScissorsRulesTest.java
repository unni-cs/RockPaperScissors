package com.agile.RockPaperScissors;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import junit.framework.TestCase;

@RunWith(Parameterized.class)
public class RockPaperScissorsRulesTest extends TestCase {
    
    RockPaperScissorsRules rules = new RockPaperScissorsRules();
    Player playerOne = new Player();
    Player playerTwo = new Player();

    RuleTestData ruleTestData;

    public RockPaperScissorsRulesTest(RuleTestData ruleTestData) {
       this.ruleTestData = ruleTestData;
    }

    //To be removed
    /*public void test_WhenSelectionMatches_ShouldReturnTie()
    {
        //Given
        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Rock;
        var expected = "TIE";

        //When
        var actual = rules.Execute(playerOne.Selection, playerTwo.Selection);

        //Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
    
    public void test_WhenSelectedRockAndScissors_ShouldReturnPlayerOne()
    {
        //Given
        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Scissors;
        var expected = "P1";

        //When
        var actual = rules.Execute(playerOne.Selection, playerTwo.Selection);

        //Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    public void test_WhenSelectedRockAndPaper_ShouldReturnPlayerTwo()
    {
        //Given
        playerOne.Selection = Options.Rock;
        playerTwo.Selection = Options.Paper;
        var expected = "P2";

        //When
        var actual = rules.Execute(playerOne.Selection, playerTwo.Selection);

        //Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    public void test_WhenSelectedPaperAndRock_ShouldReturnPlayerTwo()
    {
        //Given
        playerOne.Selection = Options.Paper;
        playerTwo.Selection = Options.Rock;
        var expected = "P1";

        //When
        var actual = rules.Execute(playerOne.Selection, playerTwo.Selection);

        //Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
    */

    @Test
    public void test_RockPaperScissorsRule_ShouldReturnExpected(){
        //Given
        playerOne.Selection = ruleTestData.PlayerOneSelection;
        playerTwo.Selection = ruleTestData.PlayerTwoSelection;
        var expected = ruleTestData.Expected;

         //When
         var actual = rules.Execute(playerOne.Selection, playerTwo.Selection);

         //Then
         assertNotNull(actual);
         assertEquals(expected, actual);
    }

   @Parameterized.Parameters
   public static Collection<RuleTestData[]> getRuleTestData() {    
    return Arrays.asList(new RuleTestData[][] {
        { new RuleTestData(){{
            PlayerOneSelection = Options.Paper;
            PlayerTwoSelection = Options.Rock;
            Expected = "P1";
        }} },
        { new RuleTestData(){{
            PlayerOneSelection = Options.Paper;
            PlayerTwoSelection = Options.Scissors;
            Expected = "P2";
        }} },
        { new RuleTestData(){{
            PlayerOneSelection = Options.Scissors;
            PlayerTwoSelection = Options.Rock;
            Expected = "P2";
        }} },
        { new RuleTestData(){{
            PlayerOneSelection = Options.Scissors;
            PlayerTwoSelection = Options.Paper;
            Expected = "P1";
        }} },
        { new RuleTestData(){{
            PlayerOneSelection = Options.Rock;
            PlayerTwoSelection = Options.Paper;
            Expected = "P2";
        }} },
        { new RuleTestData(){{
            PlayerOneSelection = Options.Rock;
            PlayerTwoSelection = Options.Scissors;
            Expected = "P1";
        }} },
        { new RuleTestData(){{
            PlayerOneSelection = Options.Rock;
            PlayerTwoSelection = Options.Rock;
            Expected = "TIE";
        }} },
        { new RuleTestData(){{
            PlayerOneSelection = Options.Scissors;
            PlayerTwoSelection = Options.Scissors;
            Expected = "TIE";
        }} },
        { new RuleTestData(){{
            PlayerOneSelection = Options.Paper;
            PlayerTwoSelection = Options.Paper;
            Expected = "TIE";
        }} }
     });
   }
}


