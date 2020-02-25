package ui;

import core.Connect4;

import java.util.Scanner;

/**<p>Text UI for the connect 4 game</p>* */

public class Connect4TextConsole {

    public static void main(String [] args)
    {
        Connect4 game = new Connect4();
        Scanner scanner = new Scanner(System.in);
        CreatePlayersAndRunGame(scanner, game);
    }

    /**<p>Text UI for the connect 4 game</p>*
     * @param game Connect 4 game object
     * */
    public static void PrintGameBoard(Connect4 game){
        for (int i = 0; i < game.gameBoard.GetRows(); i++){
            for (int e = 0; e < game.gameBoard.GetColumns(); e++){
                System.out.print(game.gameBoard.GetPiece(i,e));
            }
            System.out.println();
        }
    }

    /**<p>Text UI for the connect 4 game</p>*
     * @param game Connect 4 game object
     * */
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

    /**<p>Checks if the column the user entered is valid</p>*
     * @param game connect 4 game object
     * @param scanner scanner for user input
     * */
    public static void RunConsoleGame(Connect4 game, Scanner scanner){
        while (true){
            game.FigureOutWhoseTurn();

            System.out.println(game.gameBoard.GetWhoseTurn().GetName() + " it's your turn! Pick a column from 1-7");
            int column = scanner.nextInt();

            while (!game.gameBoard.CheckIfColumnIsValid(column, game)){
                System.out.println("Invalid input! " + game.gameBoard.GetWhoseTurn().GetName() + " it's still your turn! Pick a column from 1-7");
                column = scanner.nextInt();
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
