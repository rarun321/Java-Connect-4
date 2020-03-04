package core;

import java.util.Random;

public class Connect4ComputerPlayer extends Player implements IConnect4ComputerPlayer{

    public Connect4ComputerPlayer(){
        super("Bot","B");
    }

    @Override
    public int MakeMove(Connect4 game, int previousColumn) {
        while(true){
            int column = getRandomNumberInRange(previousColumn - 1, previousColumn + 1);
            if(game.gameBoard.CheckIfColumnIsFull(column,game)) return column;
        }
    }

    private int getRandomNumberInRange(int min, int max) {
        Random randomNumber = new Random();
        return randomNumber.nextInt((max - min) + 1) + min;
    }
}
