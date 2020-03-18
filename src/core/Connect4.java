package core;

/**<p>Connect4 is used to create a new connect4 game</p>
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-02-23
 * */

public class Connect4 implements IConnect4 {
    public GameBoard gameBoard;
    public Player player1;
    public Player player2;

    private final int winCheck = 4;

    /**<p>Initializes new game</p>*/
    public Connect4(){
        gameBoard = new GameBoard();
        player1 = new Player();
        player2 = new Player();
    }

    /**<p>Figures out whose turn it is based on the previous players turn.</p>*/
    public void FigureOutWhoseTurn(){
        if(gameBoard.GetWhoseTurn() == null){
            gameBoard.SetWhoseTurn(player1);
            return;
        }

        if(gameBoard.GetWhoseTurn() == player1){
            gameBoard.SetWhoseTurn(player2);
            return;
        }

        if(gameBoard.GetWhoseTurn() == player2){
            gameBoard.SetWhoseTurn(player1);
            return;
        }
    }

    /**<p>Checks to see if the player has won the game</p>
     * @param row row of the piece that was the just placed in the game board
     * @param column column of the piece that was the just placed in the game board
     * @return true or false
     * */
    public boolean CheckForWin(int row, int column){
        if(CheckVertical(row,column) || CheckHorizontal(row, column) || CheckDiagonalRight(row, column) || CheckDiagonalLeft(row, column)) return true;
        return false;
    }
    /**<p>Checks to see if the game has ended in a draw</p>
     * @return true or false
     * */
    public boolean CheckForDraw(){
        for (int i = 0; i < gameBoard.GetRows(); i++){
            for(int e = 0; e < gameBoard.GetColumns(); e++) if(!(gameBoard.GetPiece(i, e).equals("| ") || gameBoard.GetPiece(i , e).equals("| |"))) return false;
        }
        return true;
    }

    /**<p>Checks to see if there is piece in the spot that matches the player piece</p>
     * @param row row on the gameboard
     * @param column column on the gameboard
     * @param player player that you want to check the piece of
     * @return true or false
     * */
    public boolean CheckIfPieceEqualsPlayerPiece(int row, int column, Player player){
        if(gameBoard.GetPiece(row, column).equals("|" + player.GetToken()) || gameBoard.GetPiece(row , column).equals("|" + player.GetToken() + "|")) return true;
        return false;
    }

    /**<p>Checks to see if it is an empty spot</p>
     * @param row row on the gameboard
     * @param column column on the gameboard
     * @return true or false
     * */
    public boolean CheckIfPieceEqualsEmpty(int row, int column){
        if(gameBoard.GetPiece(row,column).equals("| ") || gameBoard.GetPiece(row, column).equals("| |")) return true;
        return false;
    }

    private boolean CheckVertical(int row, int column){
        int pieceCount = 0;

        for (int i = row; i < gameBoard.GetRows(); i++){
            if(gameBoard.GetPiece(i, column).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(i , column).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")) pieceCount++;
            else break;
        }

        if(pieceCount == winCheck) return true;
        else return false;
    }

    private boolean CheckHorizontal(int row, int column){
        int pieceCount = 0;

        for (int i = column; i >= 0; i--){
            try{
                if(gameBoard.GetPiece(row, i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row , i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")) pieceCount++;
                else break;
            }
            catch(Exception e){ break;}
        }

        if(pieceCount == winCheck) return true;

        int temp = pieceCount;
        for (int i = 0; i <  winCheck - temp + 1; i++){
            try{
                if(gameBoard.GetPiece(row, column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row , column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")) pieceCount++;
                else break;
            }
            catch (Exception e){ break;}
        }

        if(pieceCount - 1 == winCheck) return true;
        return false;
    }

    private boolean CheckDiagonalRight(int row, int column){
        int pieceCount = 0;

        for(int i = 0; i < gameBoard.GetRows() - row; i++){
            try{
                if(gameBoard.GetPiece(row + i, column - i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row + i , column - i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")) pieceCount++;
                else break;
            }
            catch (Exception e){ break;}
        }

        if(pieceCount == 4) return true;

        int temp = pieceCount;
        for(int i = 0; i < winCheck - temp + 1 ; i++){
            try{
                if(gameBoard.GetPiece(row - i, column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row - i , column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")) pieceCount++;
                else break;
            }
            catch (Exception e){ break;}
        }

        if(pieceCount - 1 == winCheck) return true;
        return false;
    }


    private boolean CheckDiagonalLeft(int row, int column){
        int pieceCount = 0;

        for(int i = 0; i < gameBoard.GetRows() - row; i++){
            try{
                if(gameBoard.GetPiece(row + i, column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row + i , column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|"))pieceCount++;
                else break;
            }
            catch (Exception e){ break;}
        }

        if(pieceCount == winCheck) return true;

        int temp = pieceCount;
        for(int i = 0; i < winCheck - temp + 1 ; i++ ){
            try{
                if(gameBoard.GetPiece(row - i, column - i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row - i , column - i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")) pieceCount++;
                else break;
            }
            catch (Exception e){ break;}
        }

        if(pieceCount - 1 == winCheck) return true;
        return false;
    }
}