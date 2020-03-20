package core;
import java.util.Random;

/**<p>AI for the connect 4 game</p>*
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-03-03
 * */

public class Connect4ComputerPlayer extends Player implements IConnect4ComputerPlayer{
    public String difficultyLevel = "";
    ComputerDifficulty difficulty = new ComputerDifficulty();

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
    public int MakeMove(Connect4 game, int previousRow, int previousColumn) {
        if(difficultyLevel.equals("e")) return difficulty.Easy(game, previousColumn);
        if(difficultyLevel.equals("m")) return difficulty.Medium(game);
        if(difficultyLevel.equals("h")) return difficulty.Hard(game);

        return 0;
    }
}
