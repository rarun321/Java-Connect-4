package core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connect4Client{
    private Connect4 game;
    private String host = "localhost";
    private DataInputStream fromServer;
    private DataOutputStream toServer;

    public Connect4Client(Connect4 game){this.game = game;}

    public void ConnectToServer(){
        try{
            Socket socket = new Socket(host, 7000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception e){
            System.out.println("Error in ConnectToServer: " + e.getMessage());
        }
    }

    public void SendColumnServer(int column) throws IOException {
        toServer.writeInt(column);
    }
}
