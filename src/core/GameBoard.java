package core;

public class GameBoard {
    private final int rows = 6;
    private final int columns = 7;
    private String[][] gameBoard = new String[rows][columns];

    public GameBoard(){
        for (int i = 0; i < rows; i++){ //intializes empty gameboard
            for (int e = 0; e < columns; e++){
                gameBoard[i][e] = "| ";
            }
        }

        for(int i = 0; i < rows; i++){ //adds border for last column
            gameBoard[i][6] += "|";
        }
    }

    public void GameBoardPrint(){
        for (int i = 0; i < rows; i++){
            for (int e = 0; e < columns; e++){
               System.out.print(gameBoard[i][e]);
            }
            System.out.println();
        }
    }
}
