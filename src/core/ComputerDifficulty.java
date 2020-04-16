package core;

import java.util.ArrayList;

public class ComputerDifficulty {

    private final int centerColumn = 4;
    private final int linesOfTwo = 2;
    private final int linesOfThree = 5;
    private final int win = 10000;
    private final int oppLinesOfTwo = 7;
    private final int oppLinesOfThree = 1000;

    /**<p>Easy computer bot</p>
     * @param game connect4 game
     * @param depth depth of recursive call
     * @param index current index
     * @param score initial score
     * @param max initial max
     * @return column
     * */
    public int Easy(Connect4 game, int depth, int index, int score, int max) {
        if(depth == 2) return index + 1;

        for (Object obj : AIUtil(game)) {
            int i = (int)obj;
            score = CheckCenterColumn(i)
                    + CheckLinesThree(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i, game.player2)
                    + Win(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i)
                    + CheckLinesTwoOpp(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i, game.player1);
            int row = game.gameBoard.SetPiece(i, game.player1);
            Easy(game, depth + 1, index, score, max);
            game.gameBoard.RemovePiece(row, i);
            if (score > max) {
                max = score;
                index = i;
            }
        }

        return index + 1;
    }

    /**<p>Medium computer bot</p>
     * @param game connect4 game
     * @param depth depth of recursive call
     * @param index current index
     * @param score initial score
     * @param max initial max
     * @return column
     * */
    public int Medium(Connect4 game, int depth, int index, int score, int max){
        if(depth == 4) return index + 1;

        for (Object obj : AIUtil(game)) {
            int i = (int)obj;
            score = CheckCenterColumn(i)
                    + CheckLinesOfTwo(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i, game.player2)
                    + CheckLinesThree(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i, game.player2)
                    + Win(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i)
                    + CheckLinesTwoOpp(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i, game.player1)
                    + CheckLinesThreeOpp(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i, game.player1);
            int row = game.gameBoard.SetPiece(i, game.player1);
            Medium(game, depth + 1, index, score, max);
            game.gameBoard.RemovePiece(row, i);
            if (score > max) {
                max = score;
                index = i;
            }
        }

        return index + 1;
    }

    /**<p>Hard computer bot</p>
     * @param game connect4 game
     * @param depth depth of recursive call
     * @param index current index
     * @param score initial score
     * @param max initial max
     * @return column
     * */
    public int Hard(Connect4 game, int depth, int index, int score, int max){

        if(depth == 7) return index + 1;

        for (Object obj : AIUtil(game)) {
            int i = (int)obj;
            score = CheckCenterColumn(i)
                    + CheckLinesOfTwo(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i, game.player2)
                    + CheckLinesThree(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i, game.player2)
                    + Win(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i)
                    + CheckLinesTwoOpp(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i, game.player1)
                    + CheckLinesThreeOpp(game, 5 - game.GetNumberOfPiecesInGivenColumn(i + 1), i, game.player1);
            int row = game.gameBoard.SetPiece(i, game.player1);
            Hard(game, depth + 1, index, score, max);
            game.gameBoard.RemovePiece(row, i);
            if (score > max) {
                max = score;
                index = i;
            }
        }

        return index + 1;
    }

    private int CheckCenterColumn(int column){
        if(column == 3) return centerColumn;
        return 0;
    }

    private int CheckLinesOfTwo(Connect4 game, int row, int column, Player player){
        int addedScore = 0;
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row + 1, column, player)) addedScore += linesOfTwo; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row + 1, column - 1, player)) addedScore += linesOfTwo; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row - 1, column + 1, player)) addedScore += linesOfTwo; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row + 1, column + 1, player)) addedScore += linesOfTwo; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row - 1, column - 1, player)) addedScore += linesOfTwo; } catch(Exception e){}

        return addedScore;
    }

    private int CheckLinesThree(Connect4 game, int row, int column, Player player){
        int addedScore = 0;
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row + 1, column, player) && game.CheckIfPieceEqualsPlayerPiece(row + 2, column, player)) addedScore += linesOfThree; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row + 1, column - 1, player) && game.CheckIfPieceEqualsPlayerPiece(row + 2, column - 2, player)) addedScore += linesOfThree; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row - 1, column + 1, player) && game.CheckIfPieceEqualsPlayerPiece(row - 2, column + 2, player)) addedScore += linesOfThree; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row + 1, column + 1, player) && game.CheckIfPieceEqualsPlayerPiece(row + 2, column + 2, player)) addedScore += linesOfThree; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row - 1, column - 1, player) && game.CheckIfPieceEqualsPlayerPiece(row - 2, column - 2, player)) addedScore += linesOfThree; } catch(Exception e){}

        return addedScore;
    }

    private int CheckLinesTwoOpp(Connect4 game, int row, int column, Player player){
        int addedScore = 0;
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row + 1, column, player) && game.CheckIfPieceEqualsPlayerPiece(row + 2, column, player)) addedScore += oppLinesOfTwo; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row - 1, column, player) && game.CheckIfPieceEqualsPlayerPiece(row - 2, column, player)) addedScore += oppLinesOfTwo; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row, column + 1, player) && game.CheckIfPieceEqualsPlayerPiece(row, column + 2, player)) addedScore += oppLinesOfTwo; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row, column - 1, player) && game.CheckIfPieceEqualsPlayerPiece(row, column - 2, player)) addedScore += oppLinesOfTwo; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row - 1, column + 1, player) && game.CheckIfPieceEqualsPlayerPiece(row - 2, column + 2, player)) addedScore += oppLinesOfTwo; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row + 1, column + 1, player) && game.CheckIfPieceEqualsPlayerPiece(row + 2, column + 2, player)) addedScore += oppLinesOfTwo; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row - 1, column - 1, player) && game.CheckIfPieceEqualsPlayerPiece(row - 2, column - 2, player)) addedScore += oppLinesOfTwo; } catch(Exception e){}

        return addedScore;
    }

    private int CheckLinesThreeOpp(Connect4 game, int row, int column, Player player){
        int addedScore = 0;
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row + 1, column, player) && game.CheckIfPieceEqualsPlayerPiece(row + 2, column, player) && game.CheckIfPieceEqualsPlayerPiece(row + 3, column, player)) addedScore += oppLinesOfThree; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row - 1, column, player) && game.CheckIfPieceEqualsPlayerPiece(row - 2, column, player) && game.CheckIfPieceEqualsPlayerPiece(row - 3, column, player)) addedScore += oppLinesOfThree; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row, column + 1, player) && game.CheckIfPieceEqualsPlayerPiece(row, column + 2, player) && game.CheckIfPieceEqualsPlayerPiece(row, column + 3, player)) addedScore += oppLinesOfThree; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row, column - 1, player) && game.CheckIfPieceEqualsPlayerPiece(row, column - 2, player) && game.CheckIfPieceEqualsPlayerPiece(row, column - 3, player)) addedScore += oppLinesOfThree; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row - 1, column + 1, player) && game.CheckIfPieceEqualsPlayerPiece(row - 2, column + 2, player) && game.CheckIfPieceEqualsPlayerPiece(row - 3, column + 3, player)) addedScore += oppLinesOfThree; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row + 1, column + 1, player) && game.CheckIfPieceEqualsPlayerPiece(row + 2, column + 2, player) && game.CheckIfPieceEqualsPlayerPiece(row + 3, column + 3, player)) addedScore += oppLinesOfThree; } catch(Exception e){}
        try{ if(game.CheckIfPieceEqualsPlayerPiece(row - 1, column - 1, player) && game.CheckIfPieceEqualsPlayerPiece(row - 2, column - 2, player) && game.CheckIfPieceEqualsPlayerPiece(row - 3, column - 3, player)) addedScore += oppLinesOfThree; } catch(Exception e){}

        return addedScore;
    }

    private Object[] AIUtil(Connect4 game){
        ArrayList<Integer> list = new ArrayList();
        for(int i = 0; i < 7; i++){
            if (game.CheckIfPieceEqualsEmpty(0, i)) {
                list.add(i);
            }
        }

        return list.toArray();
    }

    private int Win(Connect4 game, int row, int column){
        if(row == -1) return 0;

        game.gameBoard.SetPiece(column, game.player2);
        if(game.CheckForWin(row, column) == true) {
            game.gameBoard.RemovePiece(row, column);
            return win;
        }

        game.gameBoard.RemovePiece(row, column);
        return 0;
    }
}
