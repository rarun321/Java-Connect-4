package core;

/**<p>Interface to outline Connect4ComputerPlayer class</p>*
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-03-03
 * */


public interface IConnect4ComputerPlayer {

    /**<p>Make move for the computer based on the other players turn</p>
     * @param game connect4 game
     * @return column
     * */
    int MakeMove(Connect4 game);
}
