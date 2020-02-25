package core;

public interface IConnect4 {
    /**<p>Figures out whose turn it is based on the previous players turn.</p>*/
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