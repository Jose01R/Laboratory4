module ucr.lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.base;

    opens domain to javafx.base;
    opens ucr.lab to javafx.fxml;
    exports ucr.lab;
    exports controller;
    opens controller to javafx.fxml;
}