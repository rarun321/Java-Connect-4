package ui;

import core.Connect4;

import java.util.Scanner;

public class Connect4TextConsole {
    public static void main(String [] args)
    {
        Connect4 game = new Connect4();
        Scanner scanner = new Scanner(System.in);
        CreatePlayersAndRunGame(scanner, game);
    }

    public static void PrintGameBoard(Connect4 game){
        for (int i = 0; i < game.gameBoard.GetRows(); i++){
            for (int e = 0; e < game.gameBoard.GetColumns(); e++){
                System.out.print(game.gameBoard.GetPiece(i,e));
            }
            System.out.println();
        }
    }

    public static void CreatePlayersAndRunGame(Scanner scanner, Connect4 game){
        System.out.println("Player One Name:");
        String playerOneName = scanner.nextLine();
        game.player1.SetName(playerOneName);
        System.out.println("Player One Token:");
        String playerOneToken = scanner.nextLine();
        game.player1.SetToken(playerOneToken);

        System.out.println("Player Two Name:");
        String playerTwoName = scanner.nextLine();
        game.player2.SetName(playerTwoName);
        System.out.println("Player Two Token:");
        String playerTwoToken = scanner.nextLine();
        game.player2.SetToken(playerTwoToken);

        RunConsoleGame(game, scanner);
    }

    public static void RunConsoleGame(Connect4 game, Scanner scanner){
        while (true){
            game.FigureOutWhoseTurn();
            System.out.println(game.gameBoard.GetWhoseTurn().GetName() + " it's your turn! Pick a column from 1-7");
            int column = Integer.valueOf(scanner.nextLine());
            int row = game.gameBoard.SetPiece(column - 1, game.gameBoard.GetWhoseTurn());
            PrintGameBoard(game);
            if(game.CheckForWin(row, column - 1)) break;
        }

        System.out.println(game.gameBoard.GetWhoseTurn().GetName() + " is the winner!");
    }
}
