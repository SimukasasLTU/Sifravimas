module com.example.sifravimas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sifravimas to javafx.fxml;
    exports com.example.sifravimas;
}