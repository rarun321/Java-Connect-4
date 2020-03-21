package ui;

import core.Connect4;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Connect4GUI extends Application {

    public static Connect4 game = new Connect4();
    private final GridPane boardPane = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        CreateGUIBoard(stage);
    }

    public void CreateGUIBoard(Stage stage){

        boardPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        for(int i = 0; i < game.gameBoard.GetColumns(); i++){
            for(int e = 0; e < game.gameBoard.GetRows(); e++){
                Rectangle rectangle = new Rectangle(100,100);
                rectangle.setFill(Color.BLUE);
                boardPane.add(rectangle,i,e);
            }
        }

        for(int i = 0; i < game.gameBoard.GetColumns(); i++){
            for(int e = 0; e < game.gameBoard.GetRows(); e++){
                Circle circle = new Circle(300,0,45);
                circle.centerXProperty();
                circle.setStroke(Color.WHITE);
                circle.setFill(Color.WHITE);
                circle.setStrokeWidth(10);
                boardPane.setVgap(7);
                boardPane.setHgap(7);
                boardPane.add(circle,i,e);
            }
        }

        for(int i = 0; i < game.gameBoard.GetColumns(); i++){
            Button button = new Button("Column " + (i + 1));
            button.setMinWidth(100);
            button.setOnAction(e-> RunGame(e));
            boardPane.add(button,i,8);
        }

        Scene scene = new Scene(boardPane, 745, 700);
        stage.setTitle("Connect 4");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void RunGame(ActionEvent e){
        String buttonText = ((Button)e.getSource()).getText();
        if(buttonText.equals("Column 1")){
            FillPiece(game.gameBoard.SetPiece(0,game.gameBoard.GetWhoseTurn()),0);
            game.FigureOutWhoseTurn();
        }
        else if(buttonText.equals("Column 2")){
            FillPiece(game.gameBoard.SetPiece(1,game.gameBoard.GetWhoseTurn()),1);
            game.FigureOutWhoseTurn();
        }
        else if(buttonText.equals("Column 3")){
            FillPiece(game.gameBoard.SetPiece(2,game.gameBoard.GetWhoseTurn()),2);
            game.FigureOutWhoseTurn();
        }
        else if(buttonText.equals("Column 4")){
            FillPiece(game.gameBoard.SetPiece(3,game.gameBoard.GetWhoseTurn()),3);
            game.FigureOutWhoseTurn();
        }
        else if(buttonText.equals("Column 5")){
            FillPiece(game.gameBoard.SetPiece(4,game.gameBoard.GetWhoseTurn()),4);
            game.FigureOutWhoseTurn();
        }
        else if(buttonText.equals("Column 6")){
            FillPiece(game.gameBoard.SetPiece(5,game.gameBoard.GetWhoseTurn()),5);
            game.FigureOutWhoseTurn();
        }
        else if(buttonText.equals("Column 7")){
            FillPiece(game.gameBoard.SetPiece(6,game.gameBoard.GetWhoseTurn()),6);
            game.FigureOutWhoseTurn();
        }
    }

    public void FillPiece(int row, int column){
        Circle circle = new Circle(300,0,45);
        circle.centerXProperty();
        if(game.gameBoard.GetWhoseTurn() == game.player1){
            circle.setStroke(Color.RED);
            circle.setFill(Color.RED);
        }
        else{
            circle.setStroke(Color.YELLOW);
            circle.setFill(Color.YELLOW);
        }
        circle.setStrokeWidth(10);
        boardPane.add(circle, column,row);
    }
}
