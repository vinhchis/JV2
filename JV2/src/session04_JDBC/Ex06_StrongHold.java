package session04_JDBC;

import session04_JDBC.DBConnect;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class Ex06_StrongHold {

    Connection cnn;
    String account = "sa";
    String password = "123456";
    String database = "StrongHold";

    PreparedStatement st;
    ResultSet rs;

    //
    DefaultTableModel model;
    //

    private JLabel lCode;
    private JLabel lName;
    private JLabel lPrice;
    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtPrice;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JTable tbItem;

    public Ex06_StrongHold() {
        cnn = DBConnect.makeConnection(account, password, database);
        model = new DefaultTableModel();

        String[] cols = {"Code","Name","Price"};
        model.setColumnIdentifiers(cols);
        loadData();
    }

    void loadData() {
        String query = "select * from Item";
        try {
            st = cnn.prepareStatement(query);
            rs = st.executeQuery();
            model.setRowCount(0);
            while(rs.next()){
                Vector tmp = new Vector();
                tmp.add(rs.getString(1));
                tmp.add(rs.getString(2));
                tmp.add(rs.getInt(3));
                model.addRow(tmp);
            }
            tbItem.setModel(model);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void main(String[] args) {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
