package core;

/**<p>Interface used to outline game board class</p>
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-02-23
 * */

public interface IGameBoard {
    /**<p>Gets the number of rows in a connect 4 board</p>
     * @return rows in board
     * */
    int GetRows();

    /**<p>Gets the number of columns in a connect 4 board</p>
     * @return columns in board
     * */
    int GetColumns();

    /**<p>Returns the value at position specified by the user on the board</p>
     * @param row any row on the game board
     * @param column any column on the game board
     * @return string that is at the position specified
     * */
    String GetPiece(int row, int column);

    /**<p>Sets the value at the position specified by the user on the board</p>
     * @param column any column on the game board
     * @param player player whose turn it is
     * @return row that the piece was placed on
     * */
    int SetPiece(int column, Player player);

    /**<p>Gets the player whose turn it is currently</p>
     * @return the player
     * */
    Player GetWhoseTurn();

    /**<p>Sets the player whose turn it is going to be</p>
     * @param player next player
     * */
    void SetWhoseTurn(Player player);

    /**<p>Checks if the column the user entered is valid</p>*
     * @param column column that the user entered
     * @param game connect 4 game object
     * @return true or false
     * */
    boolean CheckIfColumnIsFull(int column, Connect4 game);
}