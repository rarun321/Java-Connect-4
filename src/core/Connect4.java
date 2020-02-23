package core;

public class Connect4 implements IConnect4 {
    public GameBoard gameBoard;
    public Player player1;
    public Player player2;

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

       if(row <= 2){ //checks vertical
           for (int i = row; i < gameBoard.GetRows(); i++){
               if(gameBoard.GetPiece(row, i).equals("|" + gameBoard.GetWhoseTurn().GetToken())) pieceCount++;
               else break;
           }

           if(pieceCount == 4) return true;
           else pieceCount = 0;
       }

       if(column >= 3){ //check horizontal left
           for (int i = column; i >= 0; i--){
               if(gameBoard.GetPiece(row, i).equals("|" + gameBoard.GetWhoseTurn().GetToken())) pieceCount++;
               else break;
           }

           if(pieceCount == 4) return true;
           else pieceCount = 0;
       }

        if(column < 3){ //check horizontal right
            for (int i = column; i < gameBoard.GetColumns(); i++){
                if(gameBoard.GetPiece(row, i).equals("|" + gameBoard.GetWhoseTurn().GetToken())) pieceCount++;
                else break;
            }

            if(pieceCount == 4) return true;
            else pieceCount = 0;
        }

      return false;
    }

    public boolean CheckForDraw(){
        return true;
    }
}
