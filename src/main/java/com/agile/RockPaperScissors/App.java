package com.agile.RockPaperScissors;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Player playerOne;
    private static Player playerTwo;
    private static Scanner scanner = new Scanner(System.in);
    private static RockPaperScissorsGame game = new RockPaperScissorsGame();
    private static int AttemptsLeft = 2;
    
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "      Welcome to Rock Paper Scissors Game!" );
        System.out.println( "------------------------------------------------" );
        DisplayGameRules();
        System.out.println( "Press 1 to start the game, 0 to exit" );
        int userInput = scanner.nextInt();
        if(userInput == 1){
            playerOne = RegisterPlayer("1");
            playerTwo = RegisterPlayer("2");

            System.out.println( " " );
        
            game.Start();
            game.PlayerOne = playerOne;
            game.PlayerTwo = playerTwo;      
            
            Play();           
        }
        else{
            ExitApp();
        }        
    }

    public static void ExitApp(){
        scanner.close();
        System.out.println( "Closing" );
        System.exit(0);
    }

    public static void TryAgain() throws Exception{
        System.out.println( "Wanna try again? Press 1 to play again, 2 to view leader status, 0 to exit" );
        int userInput = scanner.nextInt();
        if(userInput == 1){
            game.Start();            
            Play();
        } 
        if(userInput == 2){            
            PrintLeaderStatus();
            TryAgain();
        }
        if(userInput == 0){
            ExitApp();
        }
        else{
            System.out.println( "Invalid option! Try again!" );
            TryAgain();
        }  
    }

    public static void DisplayGameRules(){
        System.out.println( "Played by two people." );
        System.out.println( "Three rounds in a game." );
        System.out.println( "You can choose one of the below option for each round.");
        System.out.println( "Rock - 1 / Paper - 2 / Scissors - 3 ");
        System.out.println( "         Rules for winning is as below.");
        System.out.println( "------------------------------------------------" );
        System.out.println( "Rule1: Rock and Paper : Paper wins");
        System.out.println( "Rule2: Rock and Scissors : Rock wins");
        System.out.println( "Rule3: Paper and Scissors : Scissors wins");
        System.out.println( "Rule4: If the selection is same : Tie");
        System.out.println( "Rule5: The player who won the most rounds will be the game winner");
    }

    public static Player RegisterPlayer(String tag){
        System.out.println( "Enter Player" + tag +" name:");
        String playerName = scanner.next();
        return new Player(){{ Name = playerName; }};
    }

    public static void SelectAnOption(Player player){        
        System.out.println( "Player :" + player.Name);
        System.out.println("Choose your option:  Rock - 1 / Paper - 2 / Scissors - 3 ");
        int playerOption = scanner.nextInt();
        if(playerOption == 1){
            player.Selection = Options.Rock;
        }
        else if(playerOption == 2){
            player.Selection = Options.Paper;
        }
        else if(playerOption == 3){
            player.Selection = Options.Scissors;
        }
        else{
            System.out.println("Invalid option. Try again! You have "+AttemptsLeft+" attempt/attempts left");            
            if(AttemptsLeft == 0){
                ExitApp();
            }
            AttemptsLeft--;
            SelectAnOption(player);            
        }
    }

    public static void Play() throws Exception {
        for(int round = 1; round <= game.MaxRounds; round++){
            System.out.println( "Round " + round );
            System.out.println( "-----------------------------------" );
            SelectAnOption(playerOne);
            SelectAnOption(playerTwo);
            
            game.Play();
            System.out.println( "-----------------------------------" );
            PrintGameStatus(playerOne);            
        }
        game.Stop();
        TryAgain();
    }

    public static void PrintGameStatus(Player player){
        GameStatus currentGame = player.LeaderBoard.CurrentGame;
        System.out.println( " ");
        System.out.println( "Status");
        if(currentGame != null){            
            System.out.println( "====================================================================================");
            System.out.println( "Round1 Winner       |Round2 Winner       |Round3 Winner       |Game Winner         |");
            System.out.println( "====================================================================================");

            String names = "";
            if(currentGame.Rounds.size()>0){
                for (GameRound round : currentGame.Rounds) 
                {
                    names += String.format("%-20s",  round.Winner) + "|";                 
                }                
                if(currentGame.CurrentRound == 3){
                    names +=  String.format("%-20s",  currentGame.Winner) + "|";     
                }                
                System.out.println(names);
            }
            else{
                System.out.println( "NA                  |NA                  |NA                  |NA                  |");
            }
        }        
        System.out.println( "------------------------------------------------------------------------------------");
        System.out.println( " ");
    }

    public static void PrintLeaderStatus(){
        System.out.println( " ");
        System.out.println( "Leader Status");
        System.out.println( "====================================================================================");
        System.out.println( "Round1 Winner       |Round2 Winner       |Round3 Winner       |Game Winner         |");
        System.out.println( "====================================================================================");

        for (GameStatus gameStatus : playerOne.LeaderBoard.LeaderStatus) {
            String names = "";
            for (GameRound round : gameStatus.Rounds) 
            {
                names += String.format("%-20s",  round.Winner) + "|";                 
            }                
            if(gameStatus.CurrentRound == 3){
                names +=  String.format("%-20s",  gameStatus.Winner) + "|";     
            }            
            System.out.println(names);            
            System.out.println( "------------------------------------------------------------------------------------");
        }
        System.out.println( " ");
    }
}
