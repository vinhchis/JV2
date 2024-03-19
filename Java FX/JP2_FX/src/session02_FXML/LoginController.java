/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package session02_FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author levin
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnSignUp;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnSignIn;
    
    Connection cnn;
    String account = "sa";
    String password = "123456";
    String database = "StrongHold";

    PreparedStatement st;
    ResultSet rs;
    
    public LoginController(){
        cnn = DBConnect.makeConnection(account, password, database);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleActionEvent(ActionEvent event) {
        if(event.getSource() == btnSignIn){
//            new Alert(Alert.AlertType.INFORMATION, "SignIn").showAndWait();
            login();
        }
        
        if(event.getSource() == btnSignUp){
            new Alert(Alert.AlertType.INFORMATION, "SignUp").showAndWait();
        }
    }
    
   private void login(){
       String name = txtUsername.getText().trim();
       String pass = txtPassword.getText().trim();
       

        String query = "select * from [user] where username = ? and password = ?";
        try {
            st = cnn.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, pass);

            rs = st.executeQuery();
            if (name.isBlank()) {
                new Alert(Alert.AlertType.ERROR, "Username cannot blank!").show();
            } else if (pass.isBlank()) {
                new Alert(Alert.AlertType.ERROR, "Password cannot blank!").show();
            } else {
                if (!rs.next()) {
                    new Alert(Alert.AlertType.ERROR, "Username or Password is wrong!").show();
                } else {
                    loadDashboard();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
       
   }
   
   private void loadDashboard() throws IOException{
        btnSignIn.getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        
        
        Stage stage = new Stage();
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(root));
        stage.show();
   }
   
   
}
