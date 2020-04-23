package test;

import core.Connect4;
import core.Connect4ComputerPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerDifficultyTest {

    Connect4 testConnect4;
    Connect4ComputerPlayer testComputerPlayer;

    @BeforeEach
    public void setUp(){
        testConnect4 = new Connect4();
        testConnect4.player1.SetToken("X");
        testComputerPlayer = new Connect4ComputerPlayer();
    }

    @Test
    void easy() { //checks lines of three for opponent
        testConnect4.FigureOutWhoseTurn();

        for(int i = 0; i < 3; i++){
            testConnect4.gameBoard.SetPiece(i, testConnect4.player1);
        }

        testComputerPlayer.difficultyLevel = "e";
        assert testComputerPlayer.MakeMove(testConnect4) == 4 : "Computer Player didn't make the most precise move";
    }

    @Test
    void medium(){ //checks lines of three for itself
        testConnect4.FigureOutWhoseTurn();

        for(int i = 0; i < 3; i++){
            testConnect4.gameBoard.SetPiece(5, testConnect4.player2);
        }

        testComputerPlayer.difficultyLevel = "m";
        assert testComputerPlayer.MakeMove(testConnect4) == 6 : "Computer Player didn't make the most precise move";
    }

    @Test
    void hard(){ //checks lines of three for itself
        testConnect4.FigureOutWhoseTurn();

        for(int i = 0; i < 3; i++){
            testConnect4.gameBoard.SetPiece(2, testConnect4.player2);
        }

        testComputerPlayer.difficultyLevel = "h";
        assert testComputerPlayer.MakeMove(testConnect4) == 3 : "Computer Player didn't make the most precise move";
    }
}