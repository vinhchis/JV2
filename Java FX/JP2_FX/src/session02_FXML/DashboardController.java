/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package session02_FXML;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author levin
 */
public class DashboardController implements Initializable {

    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Item> tbItems;
    @FXML
    private TableColumn<Item, String> colCode;
    @FXML
    private TableColumn<Item, String> colName;
    @FXML
    private TableColumn<Item, Integer> colPrice;

    Connection cnn;
    String account = "sa";
    String password = "123456";
    String database = "StrongHold";

    PreparedStatement st;
    ResultSet rs;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnn = DBConnect.makeConnection(account, password, database);
        loadTable();
    }    

    @FXML
    private void handleActionEvent(ActionEvent event) {
        if(event.getSource() == btnInsert){
            insert();
            loadTable();
        }
        
        if(event.getSource() == btnDelete){
            delete();
        }
    }

    @FXML
    private void handleMouseClicked(MouseEvent event) {
        if(event.getSource() == tbItems){
            getItemSelected(event);
        }
    }
    
    private ObservableList<Item> select() throws SQLException{
        ObservableList<Item> list = FXCollections.observableArrayList();
        String query = "select * from Item";
        
        st = cnn.prepareStatement(query);
        rs = st.executeQuery();
        
        while(rs.next()){
            Item item;
            var code = rs.getString(1);
            var name = rs.getString(2);
            var price = rs.getInt(3);
            
            item = new Item(code, name, price);
            
            list.add(item);
           
        }
        return list;
    }
    
    private void insert(){
        var code = txtCode.getText().trim();
        var name = txtName.getText().trim();
        var price = Integer.parseInt(txtPrice.getText().trim());
        
         String query = "insert into Item values (?, ?, ?)";
        try {
            st = cnn.prepareStatement(query);
            st.setString(1, code);
            st.setString(2, name);
            st.setInt(3, price);
            int count = st.executeUpdate();
            if (count != 0) {
                System.out.println("Successfully");
                clearFields();
            } else {
                System.out.println("Fail");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    private void delete(){
        var code = txtCode.getText().trim();
        var name = txtName.getText().trim();
        var price = Integer.parseInt(txtPrice.getText().trim());
        
         String query = "insert into Item values (?, ?, ?)";
        try {
            st = cnn.prepareStatement(query);
            st.setString(1, code);
            st.setString(2, name);
            st.setInt(3, price);
            int count = st.executeUpdate();
            if (count != 0) {
                System.out.println("Successfully");
                clearFields();
            } else {
                System.out.println("Fail");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void update(){
    
    }
    private void clearFields(){
        txtCode.setText("");
        txtName.setText("");
        txtPrice.setText("");
    }
    
    private void getItemSelected(MouseEvent e){
        var item = tbItems.getSelectionModel().getSelectedItem();
        txtCode.setText(item.getCode());
        txtName.setText(item.getName());
        txtPrice.setText(String.valueOf(item.getPrice()));

    }
    
    private void loadTable(){
//        ObservableList<Item> list = null;
//        try { 
//            list = select();
//            
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        
//        colCode.setCellValueFactory(new PropertyValueFactory<Item, String>("code"));
//        colName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
//        colPrice.setCellValueFactory(new PropertyValueFactory<Item, Integer>("price"));
//        
//        tbItems.setItems(list);

    ObservableList<Item> list = null;
        try {
            list = select();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        colCode.setCellValueFactory(new PropertyValueFactory<Item, String>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Item, Integer>("price"));
        tbItems.setItems(list);
        
       
       
   }
    
}
