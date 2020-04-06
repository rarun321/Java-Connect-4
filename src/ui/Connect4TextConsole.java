package ui;

import core.*;
import javafx.application.Application;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Scanner;

/**<p>Text UI for the connect 4 game</p>*
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-02-23
 * */

public class Connect4TextConsole {
    public static void main(String[] args) throws IOException {
        Connect4 game = new Connect4();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ‘T’ if you want to play using a text console; enter ‘G’ to play using a gui.");
        String platform = scanner.nextLine();
        while(true){
            if(platform.toLowerCase().equals("t")) break;
            else if(platform.toLowerCase().equals("g")) break;
            else{
                System.out.println("Invalid input!");
                platform = scanner.nextLine();
            }
        }

        System.out.println("Enter ‘P’ if you want to play against another player; enter ‘C’ to play against computer.");
        String answer = scanner.nextLine();
        while(true){
            if(answer.toLowerCase().equals("p")){
                CreatePlayers(scanner, game);
                if(platform.toLowerCase().equals("t")) RunConsoleGameOnline(game);
                else RunGUIGameLocal(game, args);
                break;
            }
            else if(answer.toLowerCase().equals("c")){
                CreateComputer(scanner, game);
                if(platform.toLowerCase().equals("t")) RunConsoleGameLocal(game,scanner);
                else RunGUIGameLocal(game, args);
                break;
            }
            else{
                System.out.println("Invalid input!");
                answer = scanner.nextLine();
            }
        }
    }

    private static void CreatePlayers(Scanner scanner, Connect4 game){
        game.player1 = new Player("Player 1", "X");
        game.player2 = new Player("Player 2", "Y");
    }

    private static void CreateComputer(Scanner scanner, Connect4 game){
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

    private static void RunConsoleGameLocal(Connect4 game, Scanner scanner){
        int column;
        int row;
        String value;

        while (true){
            game.FigureOutWhoseTurn();

            if(game.gameBoard.GetWhoseTurn().GetName() != "Bot"){
                System.out.println(game.gameBoard.GetWhoseTurn().GetName() + " it's your turn! Pick a column from 1-7");
                value = scanner.nextLine();

                while(!value.matches("[1-7]+")){
                    System.out.println("Invalid input! " + game.gameBoard.GetWhoseTurn().GetName() + " it's still your turn! Pick a column from 1-7");
                    value = scanner.nextLine();
                }

                column = Integer.valueOf(value);

                while (!game.gameBoard.CheckIfColumnIsFull(column, game)){
                    System.out.println("Invalid input! " + game.gameBoard.GetWhoseTurn().GetName() + " it's still your turn! Pick a column from 1-7");
                    column = scanner.nextInt();
                }
            }
            else{
                column = ((Connect4ComputerPlayer)game.gameBoard.GetWhoseTurn()).MakeMove(game);
                System.out.println();
            }

            row = game.gameBoard.SetPiece(column - 1, game.gameBoard.GetWhoseTurn());
            game.gameBoard.PrintGameBoard(game);

            if(game.CheckForWin(row, column - 1)) {
                System.out.println(game.gameBoard.GetWhoseTurn().GetName() + " is the winner!");
                break;
            }
            else if(game.CheckForDraw()){
                System.out.println("It's a draw!");
                break;
            }
        }
    }

    private static void RunGUIGameLocal(Connect4 game, String[] args){
        Connect4GUI gui = new Connect4GUI();
        gui.game = game;

        Application.launch(Connect4GUI.class, args);
    }

    private static void RunConsoleGameOnline(Connect4 game) throws IOException {
        Connect4Client client = new Connect4Client(game);
        client.ConnectToServer();
    }

    public static void RunClientBoards(Connect4Client client, Connect4 game){
        Scanner scanner = new Scanner(System.in);
        int column;
        String value;
        if(client.player.myTurn) {
            System.out.println("Player " + client.player.playerPosition + " it's your turn! Pick a column from 1-7");
            value = scanner.nextLine();
            while (!value.matches("[1-7]+")) {
                System.out.println("Invalid input! " + "Player " + client.player.playerPosition + " it's still your turn! Pick a column from 1-7");
                value = scanner.nextLine();
            }

            column = Integer.valueOf(value);

            while (!game.gameBoard.CheckIfColumnIsFull(column, game)) {
                System.out.println("Invalid input! " + "Player " + client.player.playerPosition + " it's still your turn! Pick a column from 1-7");
                column = scanner.nextInt();
            }

            game.gameBoard.SetPiece(column - 1, client.player);
            game.gameBoard.PrintGameBoard(game);
            client.player.myTurn = false;
            System.out.println();
            try {
                client.SendColumnToServer(column - 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
