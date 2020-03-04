package core;
import java.util.Random;

/**<p>AI for the connect 4 game</p>*
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-03-03
 * */

public class Connect4ComputerPlayer extends Player implements IConnect4ComputerPlayer{

    /**<p>Initializes new computer player</p>*/
    public Connect4ComputerPlayer(){
        super("Bot","B");
    }

    /**<p>Make move for the computer based on the other players turn</p>
     * @param game connect4 game
     * @param previousColumn column of the piece that was placed by previous player
     * @return column
     * */
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
