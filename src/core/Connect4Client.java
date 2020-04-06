package core;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import ui.Connect4GUI;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connect4Client implements ServerMessages{
    private Connect4GUI gui;
    private String host = "localhost";
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    public Player player;
    public Player otherPlayer;

    public Connect4Client(Connect4GUI gui){
        this.gui = gui;
    }

    public void ConnectToServer(){
        try{
            Socket socket = new Socket(host, 7000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

            int position = fromServer.readInt();
            if(position == 1){
               player = new Player("Player 1", "X", 1, true);
               otherPlayer = new Player("Player 2", "Y", 1, false);
            }
            else{
                player = new Player("Player 2", "Y", 2, false);
                otherPlayer = new Player("Player 1", "X", 1, false);
            }
        }
        catch (Exception e){
            System.out.println("Error in ConnectToServer: " + e.getMessage());
        }

        new Thread(()->{
            while (true){
                    try {
                        int info = receiveInfoFromServer();
                        if(info == Win){
                            if(player.myTurn) SendWinNotification(player.playerPosition);
                            else SendWinNotification(otherPlayer.playerPosition);
                        }
                        else if(info == Draw){
                            SendDrawNotification();
                        }
                        else{
                            UpdateBoard(info);
                            player.myTurn = true;
                            System.out.print(info);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }).start();
    }

    public void SendColumnToServer(int column) throws IOException {
        toServer.writeInt(column);
    }

    private void UpdateBoard(int column){
        Platform.runLater(()-> gui.MakeMove(column, otherPlayer));
    }

    private void SendWinNotification(int position){
        Platform.runLater(()-> gui.SendAlert("Player " + position + " has won!", ButtonType.FINISH));
    }

    private void SendDrawNotification(){
        Platform.runLater(()-> gui.SendAlert("Draw!", ButtonType.FINISH));
    }

    private int receiveInfoFromServer() throws IOException {
        int status = fromServer.readInt();
        return status;
    }
}
