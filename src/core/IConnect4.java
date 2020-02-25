package core;

/**<p>Interface to outline Connect4 class</p>*
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-02-23
 * */

public interface IConnect4 {
    /**<p>Figures out whose turn it is based on the previous players turn.</p>
     * */
    void FigureOutWhoseTurn();

    /**<p>Checks to see if the player has won the game</p>
     * @param row row of the piece that was the just placed in the game board
     * @param column column of the piece that was the just placed in the game board
     * @return true or false
     * */
    boolean CheckForWin(int row, int column);

    /**<p>Checks to see if the game has ended in a draw</p>
     * @return true or false
     * */
    boolean CheckForDraw();
}