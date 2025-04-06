package controller;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddStudentAddFirstController {

    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField textFieldName;
    @javafx.fxml.FXML
    private TextField textFieldStudentId;
    @javafx.fxml.FXML
    private TextField textFieldAge;
    @javafx.fxml.FXML
    private TextField textFieldAddress;

    private SinglyLinkedList studentList;
    private Alert alert;
    private StudentController studentController;

    public void setStudentController(StudentController controller) {
        this.studentController = controller;
    }

    @javafx.fxml.FXML
    public void initialize() {
        this.studentList = util.Utility.getStudentList();
        alert = util.FXUtility.alert("Student List", "Add Student");
    }

    @javafx.fxml.FXML
    public void addStudentOnAction(ActionEvent actionEvent) {

        // Obtener los valores de los campos
        String id = textFieldStudentId.getText().trim();
        String name = textFieldName.getText().trim();
        String ageText = textFieldAge.getText().trim();
        String address = textFieldAddress.getText().trim();


        // Validar la entrada
        if (id.isEmpty() || name.isEmpty() || ageText.isEmpty() || address.isEmpty()) {
            util.FXUtility.alert("Error", "Fields are necessary.");
            return;
        }

        try {
            int age = Integer.parseInt(ageText); // Convertir la edad a entero

            if (idAlreadyExists(id.trim())) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setHeaderText("Ya existe un estudiante con ese ID.");
                alert.show();
                textFieldStudentId.clear();
                return;
            }
            // Crear un nuevo estudiante
            Student student = new Student(id, name, age, address);


            // Agregarlo a la lista (esto asume que tienes el método addFirst)
            studentList.addFirst(student);
            util.Utility.setStudentList(studentList);

            // Mostrar un mensaje de éxito
            util.FXUtility.alert("Success", "Student added correctly").showAndWait();

            if (studentController != null) {
                studentController.updateTableView();
            }

        } catch (NumberFormatException | ListException e) {
            util.FXUtility.alert("ERROR", "Age could be a valid number.");
        }

    }

    @javafx.fxml.FXML
    public void cleanStudentOnAction(ActionEvent actionEvent) {
        textFieldName.clear();
        textFieldStudentId.clear();
        textFieldAge.clear();
        textFieldAddress.clear();
    }

    @javafx.fxml.FXML
    public void closeStudentOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "student.fxml", bp);
    }

    public boolean idAlreadyExists(String id) {
        try {
            for (int i = 1; i <= studentList.size(); i++) {
                Student s = (Student) studentList.getNode(i).data;
                if (s.getId().equals(id.trim())) {
                    return true; //El iD ya existe
                }
            }
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al validar ID: " + e.getMessage());
            alert.show();
        }
        return false; //El iD no existe
    }

}
