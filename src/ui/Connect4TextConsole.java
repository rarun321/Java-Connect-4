package ui;

import core.GameBoard;

public class Connect4TextConsole {
    public static void main(String [] args)
    {
        GameBoard gameBoard = new GameBoard();
        PrintGameBoard(gameBoard);
    }

    public static void PrintGameBoard(GameBoard gameBoard){
        for (int i = 0; i < gameBoard.GetRows(); i++){
            for (int e = 0; e < gameBoard.GetColumns(); e++){
                System.out.print(gameBoard.GetValue(i,e));
            }
            System.out.println();
        }
    }
}
