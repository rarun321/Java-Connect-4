package core;

/**<p>Player is used to keep track of information about the user</p>*/
public class Player implements IPlayer {
    private String playerName;
    private String playerToken;

    public Player(){ }

    /**<p>Gets player  name</p>
     * @return player name
     * */
    public String GetName() {
        return playerName;
    }

    /**<p>Sets players name</p>
     * @param name players name
     * */
    public void SetName(String name) {
        playerName = name;
    }

    /**<p>Gets players token</p>
     * @return players token
     * */
    public String GetToken() {
        return playerToken;
    }

    /**<p>Sets players token</p>
     * @param token players token
     * */
    public void SetToken(String token) {
        playerToken = token;
    }
}