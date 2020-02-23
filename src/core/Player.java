package core;

public class Player implements IPlayer {
    private String playerName;
    private String playerToken;

    public Player(){ }

    public String GetName() {
        return playerName;
    }

    public String GetToken() {
        return playerToken;
    }

    public void SetToken(String token) {
        playerToken = token;
    }

    public void SetName(String name) {
        playerName = name;
    }
}
