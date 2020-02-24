package core;

public class Connect4 implements IConnect4 {
    public GameBoard gameBoard;
    public Player player1;
    public Player player2;

    private final int winCheck = 4;

    public Connect4(){
        gameBoard = new GameBoard();
        player1 = new Player();
        player2 = new Player();
    }

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

    public boolean CheckForWin(int row, int column){
        int pieceCount = 0;
        int tempRow = row;
        int tempCol = column;

       if(row <= 2){ //checks vertical
           for (int i = row; i < gameBoard.GetRows(); i++){
               if(gameBoard.GetPiece(i, column).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(i , column).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")){
                   pieceCount++;
               }
               else break;
           }

           if(pieceCount == 4) return true;
           else pieceCount = 0;
       }

        int front = gameBoard.GetColumns() - column - 1;
        int behind = column;

        for (int i = column; i < front; i++){
            if(gameBoard.GetPiece(row, i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row , i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")){
                pieceCount++;
            }
        }

        for (int i = 0; i < behind; i++){
            if(gameBoard.GetPiece(row, i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row , i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")){
                pieceCount++;
            }
        }

      return false;
    }

    public boolean CheckForDraw(){
        return true;
    }
}
