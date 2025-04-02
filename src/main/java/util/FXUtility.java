package util;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

public class FXUtility {

    public static Alert alert(String title, String header){
        Alert myalert = new Alert(Alert.AlertType.NONE);
        myalert.setAlertType(Alert.AlertType.ERROR);
        myalert.setTitle(title);
        myalert.setHeaderText(header);
        return myalert;
    }

    public static TextInputDialog dialog(String title, String header){
        TextInputDialog dialog = new TextInputDialog(title);
        dialog.setHeaderText(header);
        return dialog;
    }
}