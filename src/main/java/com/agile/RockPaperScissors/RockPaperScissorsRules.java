package com.agile.RockPaperScissors;

public class RockPaperScissorsRules {
    public String Execute(Options optionOne, Options optionTwo)
    {
        if(optionOne.equals(optionTwo)){
            return "TIE";
        }
        if(optionOne.equals(Options.Rock) && optionTwo.equals(Options.Scissors)){
            return "P1";
        }
        if(optionOne.equals(Options.Rock) && optionTwo.equals(Options.Paper)){
            return "P2";
        }
        if(optionOne.equals(Options.Paper) && optionTwo.equals(Options.Scissors)){
            return "P2";
        }
        if(optionOne.equals(Options.Paper) && optionTwo.equals(Options.Rock)){
            return "P1";
        }
        if(optionOne.equals(Options.Scissors) && optionTwo.equals(Options.Rock)){
            return "P2";
        }
        if(optionOne.equals(Options.Scissors) && optionTwo.equals(Options.Paper)){
            return "P1";
        }
        return null;
    }    
}
