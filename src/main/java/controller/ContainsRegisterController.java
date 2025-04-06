package controller;

import domain.DoublyLinkedList;
import domain.ListException;
import domain.Register;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ContainsRegisterController {

    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField textField;

    private DoublyLinkedList registerList;
    private Register register;
    private Alert alert;
    @javafx.fxml.FXML
    public void initialize(){
        this.registerList=util.Utility.getRegisterList();
        alert = util.FXUtility.alert("Student List", "Add Student");
    }

    @javafx.fxml.FXML
    void clearOnAction(ActionEvent event) {
        textField.clear();
    }

    @javafx.fxml.FXML
    void closeOnAction(ActionEvent event) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "register.fxml", bp);
    }

    @javafx.fxml.FXML
    void containsOnAction(ActionEvent event) {

        int id= Integer.parseInt(textField.getText().trim());
        register= new Register(id);

        boolean found = false;

        try {
            for (int i = 1; i <= registerList.size(); i++) {
                Register reg = (Register) registerList.getNode(i).data;
                if (reg.getId() == id) {
                    found = true;
                    break;
                }
            }
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
        if (found) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("The register is on the list");
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("The register is not in the list");
        }
        alert.show();
    }
    }

