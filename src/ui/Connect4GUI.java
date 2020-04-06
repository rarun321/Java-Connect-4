package ui;

import core.Connect4;
import core.Connect4ComputerPlayer;
import core.Player;
import core.ServerMessages;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Connect4GUI extends Application implements ServerMessages {

    public static Connect4 game = new Connect4();
    private static GridPane gridPane = new GridPane();

    public void start(Stage stage) {CreateGUIBoard(stage);}

    public Connect4GUI(){};

    public int MakeMove(int column, Player player){
        if(!game.CheckIfPieceEqualsEmpty(0,column)){
            SendAlert("That column is full! Choose another column!", ButtonType.OK);
            return 0;
        }
        int row = game.gameBoard.SetPiece(column, player);
        FillPiece(row,column,player);
        return row;
    }

    public void SendAlert(String message, ButtonType type){
        Alert alert = new Alert(Alert.AlertType.NONE, message, type);
        alert.showAndWait();

        if(alert.getResult() == ButtonType.FINISH) System.exit(0);
    }

    private void CreateGUIBoard(Stage stage){

        gridPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        for(int i = 0; i < game.gameBoard.GetColumns(); i++){
            for(int e = 0; e < game.gameBoard.GetRows(); e++){
                Rectangle rectangle = new Rectangle(100,100);
                rectangle.setFill(Color.BLUE);
                gridPane.add(rectangle,i,e);
            }
        }

        for(int i = 0; i < game.gameBoard.GetColumns(); i++){
            for(int e = 0; e < game.gameBoard.GetRows(); e++){
                Circle circle = new Circle(300,0,45);
                circle.centerXProperty();
                circle.setStroke(Color.WHITE);
                circle.setFill(Color.WHITE);
                circle.setStrokeWidth(10);
                gridPane.setVgap(7);
                gridPane.setHgap(7);
                gridPane.add(circle,i,e);
            }
        }

        for(int i = 0; i < game.gameBoard.GetColumns(); i++){
            Button button = new Button("Column " + (i + 1));
            button.setMinWidth(100);
            button.setOnAction(e-> {
                try {
                    RunGame(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            gridPane.add(button,i,8);
        }

        Scene scene = new Scene(gridPane, 745, 700);
        stage.setTitle("Connect 4");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void RunGame(ActionEvent e) throws IOException {
        String buttonText = ((Button)e.getSource()).getText();

        if(game.client.player.myTurn == false){
            SendAlert("It's the other players turn!", ButtonType.OK);
            return;
        }

        if(game.client.player.myTurn){
            int column = Integer.valueOf(buttonText.substring(buttonText.length() - 1)) - 1;
            MakeMove(column, game.client.player);
            game.client.SendColumnToServer(column);
            game.client.player.myTurn = false;
            return;
        }

        if(buttonText.equals("Column 1"))RunGameUtil(0, false);
        else if(buttonText.equals("Column 2"))RunGameUtil(1,false);
        else if(buttonText.equals("Column 3"))RunGameUtil(2,false);
        else if(buttonText.equals("Column 4"))RunGameUtil(3,false);
        else if(buttonText.equals("Column 5"))RunGameUtil(4,false);
        else if(buttonText.equals("Column 6"))RunGameUtil(5,false);
        else if(buttonText.equals("Column 7"))RunGameUtil(6,false);

        if(game.gameBoard.GetWhoseTurn().GetName().equals("Bot")) RunGameUtil(0,true);
    }

    private void RunGameUtil(int column, boolean bot){
        if(bot){
            int botColumn = ((Connect4ComputerPlayer)game.player2).MakeMove(game) - 1;
            int botRow = game.gameBoard.SetPiece(botColumn, game.gameBoard.GetWhoseTurn());
            FillPiece(botRow,botColumn, game.gameBoard.GetWhoseTurn());
            if(game.CheckForWin(botRow,botColumn)) SendAlert(game.gameBoard.GetWhoseTurn().GetName() + " wins!!", ButtonType.FINISH);
            if(game.CheckForDraw()) SendAlert("Draw!", ButtonType.FINISH);
            game.FigureOutWhoseTurn();
        }
        else{
            int row = MakeMove(column, game.gameBoard.GetWhoseTurn());
            if(game.CheckForWin(row,column)) SendAlert(game.gameBoard.GetWhoseTurn().GetName()+ " wins!!", ButtonType.FINISH);
            if(game.CheckForDraw()) SendAlert("Draw!", ButtonType.FINISH);
            game.FigureOutWhoseTurn();
        }
    }

    private void FillPiece(int row, int column, Player player){
        Circle circle = new Circle(300,0,45);
        circle.centerXProperty();
        if(player.GetName().equals("Player 1")){
            circle.setStroke(Color.RED);
            circle.setFill(Color.RED);
        }
        else{
            circle.setStroke(Color.YELLOW);
            circle.setFill(Color.YELLOW);
        }
        circle.setStrokeWidth(10);
        gridPane.add(circle,column,row);
    }
}
