package core;

public class GameBoard implements IGameBoard {
    public final int rows = 6;
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

    public int GetRows(){
        return rows;
    }

    public int GetColumns(){
        return columns;
    }

    public String GetValue(int row, int column){
        return gameBoard[row][column];
    }

    public void SetValue(int row, int column, Player player){
        gameBoard[row][column] = player.GetToken();
    }
}
