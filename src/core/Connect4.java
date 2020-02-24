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
      if(CheckVertical(row,column) || CheckHorizontal(row, column) || CheckDiagonalRight(row, column) || CheckDiagonalLeft(row, column)) return true;
      return false;
    }

    public boolean CheckForDraw(){
        for (int i = 0; i < gameBoard.GetRows(); i++){
            for(int e = 0; e < gameBoard.GetColumns(); e++){
                if(!(gameBoard.GetPiece(i, e).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(i , e).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|"))){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean CheckVertical(int row, int column){
        int pieceCount = 0;

           for (int i = row; i < gameBoard.GetRows(); i++){
               if(gameBoard.GetPiece(i, column).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(i , column).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")){
                   pieceCount++;
               }
               else break;
           }

           if(pieceCount == 4) return true;
           else return false;
    }

    private boolean CheckHorizontal(int row, int column){
        int pieceCount = 0;

        for (int i = column; i >= 0; i--){
            try{
                if(gameBoard.GetPiece(row, i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row , i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")){
                    pieceCount++;
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                break;
            }
        }

        if(pieceCount == winCheck) return true;

        int temp = pieceCount;
        for (int i = 0; i <  winCheck - temp + 1; i++){
            try{
                if(gameBoard.GetPiece(row, column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row , column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")){
                    pieceCount++;
                }
                else{
                    break;
                }
            }
            catch (Exception e){
                break;
            }
        }

        if(pieceCount - 1 == winCheck) return true;
        return false;
    }

    private boolean CheckDiagonalRight(int row, int column){
        int pieceCount = 0;

        for(int i = 0; i < gameBoard.GetRows() - row; i++){
            try{
                if(gameBoard.GetPiece(row + i, column - i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row + i , column - i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")){
                    pieceCount++;
                }
                else break;
            }
            catch (Exception e){
                break;
            }
        }

        if(pieceCount == 4) return true;

        int temp = pieceCount;
        for(int i = 0; i < winCheck - temp + 1 ; i++){
            try{
                if(gameBoard.GetPiece(row - i, column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row - i , column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")){
                    pieceCount++;
                }
                else break;
            }
            catch (Exception e){
                break;
            }
        }

        if(pieceCount - 1 == winCheck) return true;
        return false;
    }


    private boolean CheckDiagonalLeft(int row, int column){
        int pieceCount = 0;

        for(int i = 0; i < gameBoard.GetRows() - row; i++){
            try{
                if(gameBoard.GetPiece(row + i, column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row + i , column + i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")){
                    pieceCount++;
                }
                else break;
            }
            catch (Exception e){
                break;
            }
        }

        if(pieceCount == winCheck) return true;

        int temp = pieceCount;
        for(int i = 0; i < winCheck - temp + 1 ; i++ ){
            try{
                if(gameBoard.GetPiece(row - i, column - i).equals("|" + gameBoard.GetWhoseTurn().GetToken()) || gameBoard.GetPiece(row - i , column - i).equals("|" + gameBoard.GetWhoseTurn().GetToken() + "|")){
                    pieceCount++;
                }
                else break;
            }
            catch (Exception e){
                break;
            }
        }

        if(pieceCount - 1 == winCheck) return true;
        return false;
    }
}
