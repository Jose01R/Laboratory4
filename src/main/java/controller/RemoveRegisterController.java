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

        if (id.isEmpty()) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("The ID field must be filled.");
            alert.show();
            return;
        }

        if (registerList.isEmpty()) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("The register list is empty.");
            alert.show();
            return;
        }

        try {
            int registerId = Integer.parseInt(id); // Convertir el ID a int
            boolean removed = false;

            for (int i = 1; i <= registerList.size(); i++) {
                Register register = (Register) registerList.getNode(i).data;

                if (register.getId() == registerId) {
                    registerList.remove(register);

                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("The register has been deleted.");
                    alert.show();

                    // Opcional: Actualizar la tabla si estás volviendo a RegisterController
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText("Register not found.");
                alert.show();
            }

        } catch (NumberFormatException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid ID format. It must be a number.");
            alert.show();
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Error while removing the register: " + e.getMessage());
            alert.show();
        }

        textFieldIdRegister.clear();
    }



    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "register.fxml", bp);
    }
}
