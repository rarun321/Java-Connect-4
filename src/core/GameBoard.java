package core;

public class GameBoard implements IGameBoard {
    private final int rows = 6;
    private final int columns = 7;
    private String[][] gameBoard = new String[rows][columns];
    private Player player;

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

    public String GetPiece(int row, int column){
        return gameBoard[row][column];
    }

    public int SetPiece(int column, Player player){
       for (int i = rows - 1 ; i >= 0; i--){
           if(gameBoard[i][column] == "| "){
               gameBoard[i][column]  = "|" + player.GetToken();
               return i;
           }
       }

       return 0;
    }

    public Player GetWhoseTurn() {
        return player;
    }

    public void SetWhoseTurn(Player player) {
        this.player = player;
    }
}
