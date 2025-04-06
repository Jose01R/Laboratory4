package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.time.LocalDateTime;

public class RemoveRegisterController {
    @javafx.fxml.FXML
    private TextField textFieldIdRegister;
    @javafx.fxml.FXML
    private BorderPane bp;

    private DoublyLinkedList courseList;
    private SinglyLinkedList studentList;
    private Student student;
    private Course course;
    private Alert alert; //para el manejo de alertas
    private LocalDateTime registerDate;
    private RegisterController registerController;  // Referencia al RegisterController
    private DoublyLinkedList registerList;

    @javafx.fxml.FXML
    public void initialize(){

        this.registerList = util.Utility.getRegisterList();
        alert = util.FXUtility.alert("Register List", "Remove Register");

    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        textFieldIdRegister.clear();
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        String id = textFieldIdRegister.getText().trim();
        try {
            for (int i = 1; i <= registerList.size(); i++) {
                Register register = (Register) registerList.getNode(i).data;
                if (util.Utility.compare(register.getId(),id)==0) {
                    registerList.remove(register);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("The register has been deleted.");
                    alert.show();

                    break;//Se sale del metodo si elimina un estudiante
                }
                if(textFieldIdRegister.getText().isEmpty()) {
                    alert.setHeaderText("Text field have to be filled");
                    alert.show();
                }else
                    alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText("Register not found.");
                alert.show();
            }

        } catch (ListException e) {
            alert.setHeaderText("Error al eliminar el registro: " + e.getMessage());
            alert.show();
        }
        textFieldIdRegister.clear();
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "register.fxml", bp);
    }
}
