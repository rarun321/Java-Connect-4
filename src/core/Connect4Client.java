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
    private Socket socket;
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    public Player player;
    public Player otherPlayer;

    public Connect4Client(Connect4GUI gui){
        this.gui = gui;
    }

    public void ConnectToServer(){
        try{
            socket = new Socket(host, 7000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

            int position = fromServer.readInt();
            if(position == 1){
               player = new Player("Player 1", "X", 1, true);
               otherPlayer = new Player("Player 2", "Y", 2, false);
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
                        if(info == WinPlayer1){
                            SendWinNotification("Player 1 has won!");
                            CloseSocket();
                            break;
                        }
                        else if(info == WinPlayer2){
                            SendWinNotification("Player 2 has won!");
                            CloseSocket();
                            break;
                        }
                        else if(info == Draw){
                            SendDrawNotification();
                            CloseSocket();
                            break;
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

    private void CloseSocket() throws IOException {
        int remainingColumn = receiveInfoFromServer();
        UpdateBoard(remainingColumn);
        socket.close();
    }

    private void UpdateBoard(int column){
        Platform.runLater(()-> gui.MakeMove(column, otherPlayer));
    }

    private void SendWinNotification(String message) throws IOException {
        Platform.runLater(()-> gui.SendAlert(message, ButtonType.FINISH));
    }

    private void SendDrawNotification()  throws IOException{
        Platform.runLater(()-> gui.SendAlert("Draw!", ButtonType.FINISH));
    }

    private int receiveInfoFromServer() throws IOException {
        int status = fromServer.readInt();
        return status;
    }
}
