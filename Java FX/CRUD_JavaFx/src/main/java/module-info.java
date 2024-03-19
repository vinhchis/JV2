module org.example.crud_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.crud_javafx to javafx.fxml;
    exports org.example.crud_javafx;
}