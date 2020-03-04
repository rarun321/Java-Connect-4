package ui;

import core.Connect4;
import core.Connect4ComputerPlayer;
import core.Player;

import java.util.Scanner;

/**<p>Text UI for the connect 4 game</p>*
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-02-23
 * */

public class Connect4TextConsole {

    public static void main(String [] args)
    {
        Connect4 game = new Connect4();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ‘P’ if you want to play against another player; enter ‘C’ to play against computer.");
        String answer = scanner.nextLine();
        if(answer.equals("P")) CreatePlayersAndRunGame(scanner, game);
        else CreateComputerAndRunGame(scanner, game);
    }

    /**<p>Text UI for the connect 4 game</p>*
     * @param game Connect 4 game object
     * */
    private static void PrintGameBoard(Connect4 game){
        for (int i = 0; i < game.gameBoard.GetRows(); i++){
            for (int e = 0; e < game.gameBoard.GetColumns(); e++) System.out.print(game.gameBoard.GetPiece(i,e));
            System.out.println();
        }
    }

    /**<p>Text UI for the connect 4 game</p>*
     * @param game Connect 4 game object
     * */
    private static void CreatePlayersAndRunGame(Scanner scanner, Connect4 game){
        System.out.println("Player One Name:");
        String playerOneName = scanner.nextLine();
        System.out.println("Player One Token:");
        String playerOneToken = scanner.nextLine();
        game.player1 = new Player(playerOneName, playerOneToken);


        System.out.println("Player Two Name:");
        String playerTwoName = scanner.nextLine();
        System.out.println("Player Two Token:");
        String playerTwoToken = scanner.nextLine();
        game.player2 = new Player(playerTwoName, playerTwoToken);

        RunConsoleGame(game, scanner);
    }

    private static void CreateComputerAndRunGame(Scanner scanner, Connect4 game){
        System.out.println("Player One Name:");
        String playerOneName = scanner.nextLine();
        System.out.println("Player One Token:");
        String playerOneToken = scanner.nextLine();
        game.player1 = new Player(playerOneName, playerOneToken);

        game.player2 = new Connect4ComputerPlayer();

        RunConsoleGame(game, scanner);
    }

    /**<p>Checks if the column the user entered is valid</p>*
     * @param game connect 4 game object
     * @param scanner scanner for user input
     * */
    private static void RunConsoleGame(Connect4 game, Scanner scanner){
        int column = 0;
        String value = "";

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
                column = ((Connect4ComputerPlayer)game.gameBoard.GetWhoseTurn()).MakeMove(game, column);
                System.out.println();
            }

            int row = game.gameBoard.SetPiece(column - 1, game.gameBoard.GetWhoseTurn());
            PrintGameBoard(game);

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
}
