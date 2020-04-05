package core;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Connect4Server {

    public static void main(String[] args)
    {
        RunAndWaitForPlayersToJoin();
    }

    public static void RunAndWaitForPlayersToJoin(){
        new Thread(() -> {
            try{
                ServerSocket serverSocket = new ServerSocket(7000);
                while(true){
                    System.out.println("Waiting for player 1 to join");
                    Socket player1 = serverSocket.accept();
                    System.out.println("Player 1 has joined");

//                    System.out.println("Waiting for player 2 to join");
//                    Socket player2 = serverSocket.accept();
//                    System.out.println("Player 2 has joined");

                    new Thread(new HandleGame(player1)).start();
                }
            }
            catch(Exception e){
                System.out.println("Error in RunGameSession: " + e.getMessage());
            }
        }).start();
    }
}

class HandleGame implements Runnable{
    private Socket player1;
    private Socket player2;
    private DataInputStream fromPlayer1;
    private DataOutputStream toPlayer1;
    private DataInputStream fromPlayer2;
    private DataOutputStream toPlayer2;
    private Connect4 game;

    public HandleGame(Socket player1){
        this.player1 = player1;
        //this.player2 = player2;

        Connect4 game = new Connect4();
        game.player1 = new Player("Player 1", "X");
        this.game = game;
        //game.player2 = new Player("Player 2", "Y");
    }

    @Override
    public void run() {
        try{
            fromPlayer1 = new DataInputStream(player1.getInputStream());
            toPlayer1 = new DataOutputStream(player1.getOutputStream());
//            fromPlayer2 = new DataInputStream(player2.getInputStream());
//            toPlayer2 = new DataOutputStream(player2.getOutputStream());
        }catch(Exception e){
            System.out.println("Error in run (Handle Class): " + e.getMessage());
        }

        while(true){
            try {
                int column = fromPlayer1.readInt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void RunGameChecks(int column){
        if(game.gameBoard.CheckIfColumnIsFull(column, game))
    }
}

