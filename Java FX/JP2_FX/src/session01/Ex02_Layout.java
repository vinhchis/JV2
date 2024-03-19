package session01;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author levin
 */
public class Ex02_Layout extends Application implements EventHandler<ActionEvent> {
    
    Button btnEast, btnWest, btnSouth, btnNorth;
    Label lblMessage;
    
    

    @Override
    public void start(Stage primaryStage) throws Exception  {
        primaryStage.setTitle("First JavaFX Application");
        btnEast = new Button();
        btnEast.setText("Hello, East");
        btnWest = new Button("Hello, West");
        btnSouth = new Button("Hello, South");
        btnNorth = new Button("Hello, North");
        lblMessage = new Label("JavaFx ...");
        
        
        // anonymous inner class
        btnEast.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                new Alert(Alert.AlertType.INFORMATION, "East says: Hello, How are you?").showAndWait();
                lblMessage.setText("East says: Hello, How are you?");
            }
        });
        
        // lamda Expression
        btnWest.setOnAction((e) -> {
            new Alert(Alert.AlertType.INFORMATION, "West says: Hello, How are you?").showAndWait();
            lblMessage.setText("West says: Hello, How are you?");
            
        });
        
        
        btnSouth.setOnAction(this);
        btnNorth.setOnAction(this);

        
        // layout
//        FlowPane layout = new FlowPane();
//        layout.setHgap(25);
//        layout.getChildren().add(btnEast);
//        layout.getChildren().add(btnWest);
//        layout.getChildren().add(btnSouth);
//        layout.getChildren().add(lblMessage);

        BorderPane layout = new BorderPane();
        layout.setLeft(btnWest);
        layout.setAlignment(btnWest, Pos.CENTER);
        layout.setRight(btnEast);
        layout.setAlignment(btnEast, Pos.CENTER);
        layout.setTop(btnNorth);
        layout.setAlignment(btnNorth, Pos.CENTER);
        layout.setBottom(btnSouth);
        layout.setAlignment(btnSouth, Pos.CENTER);
        layout.setCenter(lblMessage);

        
        
        // scene
        Scene scene = new Scene(layout, 500, 300);
        
        // add and call scene to Stage
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    
    @Override
    public void handle(ActionEvent e) {
        if(e.getSource() == btnSouth){
            new Alert(Alert.AlertType.INFORMATION, "South says: Hello, How are you?").showAndWait();
            lblMessage.setText("South says: Hello, How are you?");
        }
        
         if(e.getSource() == btnNorth){
            new Alert(Alert.AlertType.INFORMATION, "South says: North, How are you?").showAndWait();
            lblMessage.setText("South says: North, How are you?");
        }
        
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
