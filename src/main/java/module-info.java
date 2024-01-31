module com.example.regnsim {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.regnsim to javafx.fxml;
    exports com.example.regnsim;
}