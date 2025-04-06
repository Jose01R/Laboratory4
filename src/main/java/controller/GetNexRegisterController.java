package controller;

import domain.DoublyLinkedList;
import domain.ListException;
import domain.Register;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class GetNexRegisterController {


    @FXML
    private TextArea textArea;
    @FXML
    private BorderPane bp;

    @FXML
    private TextField textField;

    private Register register;
    private DoublyLinkedList registerList;
    private Alert alert;
    @javafx.fxml.FXML
    public void initialize(){
        this.registerList=util.Utility.getRegisterList();
        alert = util.FXUtility.alert("Student List", "Add Student");
    }
    @FXML
    void clearOnAction(ActionEvent event) {
        textField.clear();
    }

    @FXML
    void closeOnAction(ActionEvent event) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "register.fxml", bp);
    }

    @FXML
    void getNexOnAction(ActionEvent event) {
        int id=Integer.parseInt(textField.getText().trim());
        try {
            textArea.setText("The next register in the list is:");
            textArea.setText(String.valueOf(this.registerList.getNext(id)));
            util.Utility.setRegisterList(this.registerList); //actualizo la lista general
        } catch (ListException e) {
            alert.setHeaderText("Error : " + e.getMessage());
            alert.show();
        }
    }
}
