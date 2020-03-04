package core;

/**<p>Interface to outline Player class</p>*
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-02-23
 * */

public interface IPlayer {

    /**<p>Gets player  name</p>
     * @return player name
     * */
    String GetName();

    /**<p>Sets players name</p>
     * @param name players name
     * */
    void SetName(String name);

    /**<p>Gets players token</p>
     * @return players token
     * */
    String GetToken();

    /**<p>Sets players token</p>
     * @param token players token
     * */
    void SetToken(String token);
}
