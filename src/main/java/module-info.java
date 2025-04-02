module ucr.lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens ucr.lab to javafx.fxml;
    exports ucr.lab;
    exports controller;
    opens controller to javafx.fxml;
}