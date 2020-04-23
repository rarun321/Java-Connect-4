package test;

import core.Connect4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Connect4Test {

    public Connect4 testConnect4;

    @BeforeEach
    public void setUp(){
        testConnect4 = new Connect4();
        testConnect4.player1.SetToken("T1");
        testConnect4.player2.SetToken("T2");
    }

    @Test
    void figureOutWhoseTurnSuccess() {
        testConnect4.FigureOutWhoseTurn();
        assert testConnect4.gameBoard.GetWhoseTurn().equals(testConnect4.player1) : "Incorrect player";

        testConnect4.FigureOutWhoseTurn();
        assert testConnect4.gameBoard.GetWhoseTurn().equals(testConnect4.player2) : "Incorrect player";
    }

    @Test
    void figureOutWhoseTurnFail(){
        testConnect4.FigureOutWhoseTurn();
        assert testConnect4.gameBoard.GetWhoseTurn().equals(testConnect4.player2) : "Player 2 cannot be first";
    }

    @Test
    void checkForWinSuccessHorizontal() {
        testConnect4.FigureOutWhoseTurn();
        int row = 0;

        //check horizontal
        for(int i = 0; i < 4; i++){
            row = testConnect4.gameBoard.SetPiece(i, testConnect4.gameBoard.GetWhoseTurn());
        }
        assert testConnect4.CheckForWin(row,3) : "Win Check Failed";
    }

    @Test
    void checkForWinSuccessVertical(){
        testConnect4.FigureOutWhoseTurn();
        int row = 0;

        //check horizontal
        for(int i = 0; i < 4; i++){
            row = testConnect4.gameBoard.SetPiece(0, testConnect4.gameBoard.GetWhoseTurn());
        }
        assert testConnect4.CheckForWin(row,0) : "There aren't 4 in a row";
    }

    @Test
    void checkForWinSuccessDiagonal(){
        testConnect4.FigureOutWhoseTurn();
        testConnect4.gameBoard.SetPiece(0, testConnect4.player1);
        testConnect4.gameBoard.SetPiece(1, testConnect4.player2);
        testConnect4.gameBoard.SetPiece(1, testConnect4.player1);
        testConnect4.gameBoard.SetPiece(2, testConnect4.player2);
        testConnect4.gameBoard.SetPiece(2, testConnect4.player2);
        testConnect4.gameBoard.SetPiece(2, testConnect4.player1);
        testConnect4.gameBoard.SetPiece(3, testConnect4.player2);
        testConnect4.gameBoard.SetPiece(3, testConnect4.player2);
        testConnect4.gameBoard.SetPiece(3, testConnect4.player2);
        int row = testConnect4.gameBoard.SetPiece(3, testConnect4.player1);

        assert testConnect4.CheckForWin(row,3) : "There aren't 4 in a row";
    }

    @Test
    void checkForWinFail(){
        testConnect4.FigureOutWhoseTurn();
        int row = 0;

        //check horizontal
        for(int i = 0; i < 3; i++){
            row = testConnect4.gameBoard.SetPiece(i, testConnect4.gameBoard.GetWhoseTurn());
        }
        assert testConnect4.CheckForWin(row,3) : "There aren't 4 in a row";
    }

    @Test
    void checkForDrawSuccess() {
        testConnect4.FigureOutWhoseTurn();
        for(int i = 0; i <= 6 ; i++){
            for(int e = 0; e < 7; e++){
                testConnect4.gameBoard.SetPiece(i, testConnect4.gameBoard.GetWhoseTurn());
            }
        }

       assert testConnect4.CheckForDraw() : "Draw Check Failed";
    }

    @Test
    void checkForDrawFail() {
        testConnect4.FigureOutWhoseTurn();
        for(int i = 0; i < 6 ; i++){
            for(int e = 0; e < 7; e++){
                testConnect4.gameBoard.SetPiece(i, testConnect4.gameBoard.GetWhoseTurn());
            }
        }

        assert testConnect4.CheckForDraw() : "Draw Check Failed";
    }

    @Test
    void checkIfPieceEqualsPlayerPieceSuccess() {
        testConnect4.FigureOutWhoseTurn();
        assert testConnect4.gameBoard.GetWhoseTurn().equals(testConnect4.player1) : "Player 1 has the wrong token";

        testConnect4.FigureOutWhoseTurn();
        assert testConnect4.gameBoard.GetWhoseTurn().equals(testConnect4.player2) : "Player 2 has the wrong token";
    }

    @Test
    void checkIfPieceEqualsPlayerPieceFail() {
        testConnect4.FigureOutWhoseTurn();
        assert testConnect4.gameBoard.GetWhoseTurn().equals(testConnect4.player2) : "Player 2 has the wrong token";

        testConnect4.FigureOutWhoseTurn();
        assert testConnect4.gameBoard.GetWhoseTurn().equals(testConnect4.player1) : "Player 1 has the wrong token";
    }

    @Test
    void getNumberOfPiecesInGivenColumnSuccess() {
        testConnect4.FigureOutWhoseTurn();

        for(int i = 0; i < 4; i++){
            testConnect4.gameBoard.SetPiece(3, testConnect4.gameBoard.GetWhoseTurn());
        }
        assert testConnect4.GetNumberOfPiecesInGivenColumn(4) == 4 : "Number of Pieces in the column are wrong";
    }

    @Test
    void getNumberOfPiecesInGivenColumnFail() {
        testConnect4.FigureOutWhoseTurn();

        for(int i = 0; i < 3; i++){
            testConnect4.gameBoard.SetPiece(3, testConnect4.gameBoard.GetWhoseTurn());
        }
        assert testConnect4.GetNumberOfPiecesInGivenColumn(4) == 4 : "Number of Pieces in the column are wrong";
    }

    @Test
    void checkIfPieceEqualsEmptySuccess() {
        assert testConnect4.CheckIfPieceEqualsEmpty(2,3) : "Error, a piece was just placed there";
    }

    @Test
    void checkIfPieceEqualsEmptyFail() {
        int row = testConnect4.gameBoard.SetPiece(3, testConnect4.player1);
        assert testConnect4.CheckIfPieceEqualsEmpty(row,3) : "Error, a piece was just placed there";
    }
}