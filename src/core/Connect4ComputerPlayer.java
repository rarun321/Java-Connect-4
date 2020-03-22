package core;

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
     * @return column
     * */
    @Override
    public int MakeMove(Connect4 game) {
        if(difficultyLevel.equals("e")) return difficulty.Easy(game, 0,0,0,0);
        if(difficultyLevel.equals("m")) return difficulty.Medium(game,0,0,0,0);
        if(difficultyLevel.equals("h")) return difficulty.Hard(game,0,0,0,0);

        return 0;
    }
}
