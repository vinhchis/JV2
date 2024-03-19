/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package session01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author levin
 */
public class Ex03_Scene extends Application {
    Scene scnHome, scnNext;
    Button btnHome, btnNext;
    Label lblMessage;

    @Override
    public void start(Stage stage) throws Exception {
        lblMessage = new Label("Click button to change the next Scene");
        btnHome = new Button("To next scene");
        btnNext = new Button("To home scene");
        
        btnHome.setOnAction(e -> {
           stage.setScene(scnHome);
        });
        
        btnNext.setOnAction(e -> {
            stage.setScene(scnNext);
//            try{
//                Thread.sleep(1000);
//                lblMessage.setText("Welcome back!!");
//            }catch(Exception e){
//                
//            }

        });
        
        
        // Home scene
        VBox layoutVB = new VBox();
        layoutVB.setSpacing(25);
        layoutVB.getChildren().addAll(lblMessage, btnNext);
        scnHome = new Scene(layoutVB, 300, 400);
            
        
        // Next scene
        StackPane layoutSP = new StackPane();
        layoutSP.getChildren().add(btnHome);
        scnNext = new Scene(layoutSP, 300, 400);       
        
        stage.setScene(scnHome);
        stage.show();

        
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
