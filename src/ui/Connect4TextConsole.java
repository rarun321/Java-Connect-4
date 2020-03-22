package ui;

import core.Connect4;
import core.Connect4ComputerPlayer;
import core.Player;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Scanner;

/**<p>Text UI for the connect 4 game</p>*
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-02-23
 * */

public class Connect4TextConsole {
    public static void main(String[] args)
    {
        Connect4 game = new Connect4();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ‘P’ if you want to play against another player; enter ‘C’ to play against computer.");
        String answer = scanner.nextLine();
        while(true){
            if(answer.toLowerCase().equals("p")){
                CreatePlayersAndRunGame(scanner, game);
                RunGUIGame(game, args);
                break;
            }
            else if(answer.toLowerCase().equals("c")){
                CreateComputerAndRunGame(scanner, game);
                RunGUIGame(game, args);
                break;
            }
            else{
                System.out.println("Invalid input!");
                answer = scanner.nextLine();
            }
        }
    }

    private static void CreatePlayersAndRunGame(Scanner scanner, Connect4 game){
        game.player1 = new Player("Player 1", "X");
        game.player2 = new Player("Player 2", "Y");
    }

    private static void CreateComputerAndRunGame(Scanner scanner, Connect4 game){
        System.out.println("Enter 'E' for an easy bot, 'M' for a medium bot, and 'H' for a hard bot");
        String answerLevel = scanner.nextLine();
        while(true){
            if(answerLevel.toLowerCase().equals("e")){ break;}
            else if(answerLevel.toLowerCase().equals("m")){ break; }
            else if(answerLevel.toLowerCase().equals("h")){ break; }
            else{
                System.out.println("Invalid input!");
                answerLevel = scanner.nextLine();
            }
        }

        game.player1 = new Player("Player 1", "X");
        game.player2 = new Connect4ComputerPlayer();
        ((Connect4ComputerPlayer)game.player2).difficultyLevel = answerLevel.toLowerCase();
    }

//    private static void RunConsoleGame(Connect4 game, Scanner scanner){
//        int column = 0;
//        int row = 0;
//        String value = "";
//
//        while (true){
//            game.FigureOutWhoseTurn();
//
//            if(game.gameBoard.GetWhoseTurn().GetName() != "Bot"){
//                System.out.println(game.gameBoard.GetWhoseTurn().GetName() + " it's your turn! Pick a column from 1-7");
//                value = scanner.nextLine();
//
//                while(!value.matches("[1-7]+")){
//                    System.out.println("Invalid input! " + game.gameBoard.GetWhoseTurn().GetName() + " it's still your turn! Pick a column from 1-7");
//                    value = scanner.nextLine();
//                }
//
//                column = Integer.valueOf(value);
//
//                while (!game.gameBoard.CheckIfColumnIsFull(column, game)){
//                    System.out.println("Invalid input! " + game.gameBoard.GetWhoseTurn().GetName() + " it's still your turn! Pick a column from 1-7");
//                    column = scanner.nextInt();
//                }
//            }
//            else{
//                column = ((Connect4ComputerPlayer)game.gameBoard.GetWhoseTurn()).MakeMove(game, row,column - 1);
//                System.out.println();
//            }
//
//            row = game.gameBoard.SetPiece(column - 1, game.gameBoard.GetWhoseTurn());
//            System.out.println(game.GetNumberOfPiecesInGivenColumn(column));
//            PrintGameBoard(game);
//
//            if(game.CheckForWin(row, column - 1)) {
//                System.out.println(game.gameBoard.GetWhoseTurn().GetName() + " is the winner!");
//                break;
//            }
//            else if(game.CheckForDraw()){
//                System.out.println("It's a draw!");
//                break;
//            }
//        }
//    }

    private static void RunGUIGame(Connect4 game, String[] args){
        Connect4GUI gui = new Connect4GUI();
        game.FigureOutWhoseTurn();
        gui.game = game;
        Application.launch(Connect4GUI.class, args);
    }
}
