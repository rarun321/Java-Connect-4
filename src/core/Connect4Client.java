package core;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import ui.Connect4GUI;
import ui.Connect4TextConsole;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**<p>Client class for network game</p>
 * @author Rithvik Arun
 * @version 1.0
 * @since 2020-02-23
 * */

public class Connect4Client implements IServerMessages {
    private Connect4GUI gui;
    private Connect4 game;
    private String host = "localhost";
    private Socket socket;
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    public boolean gameover = false;
    public Player player;
    public Player otherPlayer;

    public Connect4Client(Connect4GUI gui){
        this.gui = gui;
    }

    public Connect4Client(Connect4 game){
        this.game = game;
    }

    /**<p>Connects client to server</p>* */
    public void ConnectToServer(){
        try{
            socket = new Socket(host, 7000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

            int position = fromServer.readInt();

            if(position == 1){
               player = new Player("Player 1", "X", 1, true);
               otherPlayer = new Player("Player 2", "Y", 2, false);
               System.out.println("Game will start soon, just waiting on another player");
               fromServer.readInt();
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
                    else if(info == greetingPlayer1){
                        SendNotification("You're Player 1! You get to go first!");
                        if(gui == null) Connect4TextConsole.RunClientBoards(this, game);
                    }
                    else if(info == greetingPlayer2){
                        SendNotification("You're Player 2! Wait for Player 1 to make it's move!");
                    }
                    else{
                        player.myTurn = true;
                        UpdateBoard(info);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**<p>Sends column to server</p>
     * @param column column of the piece that was the just placed in the game board
     * */
    public void SendColumnToServer(int column) throws IOException {
        toServer.writeInt(column);
    }

    private void CloseSocket() throws IOException {
        int remainingColumn = receiveInfoFromServer();
        UpdateBoard(remainingColumn);
        socket.close();
    }

    private void UpdateBoard(int column){
        if(gui != null){
            Platform.runLater(()-> gui.MakeMove(column, otherPlayer));
        } else{
            game.gameBoard.SetPiece(column, otherPlayer);
            System.out.println();
            game.gameBoard.PrintGameBoard(game);
            if(!gameover) Connect4TextConsole.RunClientBoards(this, game);
        }
    }

    private void SendWinNotification(String message) throws IOException {
        gameover = true;

        if(gui != null){
            Platform.runLater(()-> gui.SendAlert(message, ButtonType.FINISH));
        } else{
            System.out.println(message);
        }
    }

    private void SendDrawNotification()  throws IOException{
        gameover = true;

        if(gui != null){
            Platform.runLater(()-> gui.SendAlert("Draw!", ButtonType.FINISH));
        }else{
            System.out.println("Draw");
        }
    }

    private void SendNotification(String message) throws IOException{
        if(gui != null){
            Platform.runLater(()-> gui.SendAlert(message, ButtonType.OK));
        } else{
            System.out.println(message);
        }
    }

    private int receiveInfoFromServer() throws IOException {
        int status = fromServer.readInt();
        return status;
    }
}
