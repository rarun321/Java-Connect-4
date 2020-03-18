package core;

import java.util.Arrays;
import java.util.Random;

public class ComputerDifficulty {

    public int Hard(Connect4 game, int previousRow, int previousColumn){
        System.out.println(previousRow + " " + previousColumn);
        String[] H1 = new String[]{"|" + game.player1.GetToken(), "|" + game.player1.GetToken(), "|" + game.player1.GetToken(), "| "};
        String[] H2 = new String[]{"| ", "|" + game.player1.GetToken(), "|" + game.player1.GetToken(), "|" + game.player1.GetToken()};
        String[] H3 = new String[]{"|" + game.player1.GetToken(), "| ", "|" + game.player1.GetToken(), "|" + game.player1.GetToken()};
        String[] H4 = new String[]{"|" + game.player1.GetToken(), "|" + game.player1.GetToken(), "| ", "|" + game.player1.GetToken()};

        int column = 0;
        int defualtColumn = 0;

        for(int i = 0; i < 4; i++){
            String[] partition = Arrays.copyOfRange(game.gameBoard.GetCoulumBasedOfRow(previousRow), i , i + 4);
            if(Arrays.equals(partition, H1)){
                column = i + 4;
                break;
            }
            if(Arrays.equals(partition, H2)){
                column = i + 1;
                break;
            }
            if(Arrays.equals(partition, H3)){
                column = i + 2;
                break;
            }
            if(Arrays.equals(partition, H4)){
                column = i + 3;
                break;
            }
        }

        while(true){
            defualtColumn = getRandomNumberInRange(previousColumn - 1, previousColumn + 1);
            if(game.gameBoard.CheckIfColumnIsFull(defualtColumn,game)) break;
        }

        return column == 0 ? defualtColumn : column;
    }

    private int getRandomNumberInRange(int min, int max) {
        Random randomNumber = new Random();
        return randomNumber.nextInt((max - min) + 1) + min;
    }

    private int getRandomBetweenTwoNumbers(int number1, int number2) {
        Random randomNumber = new Random();
        int rand = randomNumber.nextInt((1 - 0) + 1);
        if(rand == 0) return number1 + 1;
        if(rand == 1) return number2 + 1;

        return 0;
    }
}
