package core;

/**<p>GameBoard is used to add player pieces into the board and keep track of where the pieces are</p>
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-02-23
 * */

public class GameBoard implements IGameBoard {
    private final int rows = 6;
    private final int columns = 7;
    private String[][] gameBoard = new String[rows][columns];
    private Player player;

    /**<p>Initializes empty game board</p>* */
    public GameBoard(){
        for (int i = 0; i < rows; i++){
            for (int e = 0; e < columns; e++){
                gameBoard[i][e] = "| ";
            }
        }

        for(int i = 0; i < rows; i++){ //adds border for last column
            gameBoard[i][6] += "|";
        }
    }

    /**<p>Gets the number of rows in a connect 4 board</p>
     * @return rows in board
     * */
    public int GetRows(){ return rows; }

    /**<p>Gets the number of columns in a connect 4 board</p>
     * @return columns in board
     * */
    public int GetColumns(){ return columns; }

    /**<p>Returns the value at position specified by the user on the board</p>
     * @param row any row on the game board
     * @param column any column on the game board
     * @return string that is at the position specified
     * */
    public String GetPiece(int row, int column){ return gameBoard[row][column]; }

    /**<p>Sets the value at the position specified by the user on the board</p>
     * @param column any column on the game board
     * @param player player whose turn it is
     * @return row that the piece was placed on
     * */
    public int SetPiece(int column, Player player){
        for (int i = rows - 1 ; i >= 0; i--){
            if(gameBoard[i][column].equals("| ") || gameBoard[i][column].equals("| |")){
                gameBoard[i][column] = column != 6 ? "|" + player.GetToken() : "|" + player.GetToken() + "|" ;
                return i;
            }
        }

        return 0;
    }

    public void PrintGameBoard(Connect4 game){
        for (int i = 0; i < game.gameBoard.GetRows(); i++){
            for (int e = 0; e < game.gameBoard.GetColumns(); e++) System.out.print(game.gameBoard.GetPiece(i,e));
            System.out.println();
        }
    }

    /**<p>Gets the player whose turn it is currently</p>
     * @param row row of piece
     * @param column column of piece
     * */
    public void RemovePiece(int row, int column){
        if(column == 6) gameBoard[row][column] = "| |";
        else gameBoard[row][column] = "| ";
    }

    /**<p>Gets the player whose turn it is currently</p>
     * @return the player
     * */
    public Player GetWhoseTurn() { return player; }

    /**<p>Sets the player whose turn it is going to be</p>
     * @param player next player
     * */
    public void SetWhoseTurn(Player player) { this.player = player;}

    /**<p>Checks if the column the user entered is full</p>*
     * @param column column that the user entered
     * @param game connect 4 game object
     * */
    public boolean CheckIfColumnIsFull(int column, Connect4 game){
        if((column > 7 || column < 1) || !(game.CheckIfPieceEqualsEmpty(0, column - 1))) return false;
        return true;
    }
}