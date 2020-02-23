package core;

public class Player implements IPlayer {
    private String playerName;
    private String playerToken;

    public Player(String name, String token){
        playerName = name;
        playerToken = token;
    }

    public String GetName() {
        return playerName;
    }

    public String GetToken() {
        return playerToken;
    }
}
