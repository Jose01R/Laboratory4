package controller;

import com.sun.nio.sctp.AbstractNotificationHandler;
import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class ContainsStudentController {

    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField textFieldAddress;

    @javafx.fxml.FXML
    private TextField textFieldAge;

    @javafx.fxml.FXML
    private TextField textFieldId;

    @javafx.fxml.FXML
    private TextField textFieldName;

    private SinglyLinkedList studentList;
    private Alert alert; //para el manejo de alertas
    private Student student;

    @javafx.fxml.FXML
    public void initialize(){
        //cargamos la lista general
        this.studentList = util.Utility.getStudentList();
        alert = util.FXUtility.alert("Student List", "Add Student");

    }
    @javafx.fxml.FXML
    void ageOnKeyType(KeyEvent event) {
        String character = event.getCharacter();
        // Si no es un dígito, no se permite escribir
        if (!character.matches("\\d")) {
            event.consume(); // Cancela el evento, no se escribe el caracter
            alert.setTitle("Error");
            alert.setHeaderText("Debe ser un valor númerico");
            alert.show();
            textFieldAge.clear();
        }
    }

    @javafx.fxml.FXML
    void clearOnAction(ActionEvent event) {
        textFieldId.clear();
    }

    @javafx.fxml.FXML
    void closeOnAction(ActionEvent event) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "student.fxml", bp);
    }

    @javafx.fxml.FXML
    void containsOnAction(ActionEvent event) {

        String id= textFieldId.getText().trim();
        student= new Student(id.trim());

        try {
            if (studentList.contains(student)) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("The student is on the list");
                alert.show();
            } else {

                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setHeaderText("The student is not in the list");
                alert.show();
            }
        } catch (ListException e) {
            alert.setHeaderText("Error "+e.getMessage());
            alert.show();
        }

    }

}
