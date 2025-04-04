package controller;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddStudentAddSortedController
{
    @javafx.fxml.FXML
    private TextField textFieldName;
    @javafx.fxml.FXML
    private TextField textFieldStudentId;
    @javafx.fxml.FXML
    private TextField textFieldAge;
    @javafx.fxml.FXML
    private TextField textFieldAddress;
    @javafx.fxml.FXML
    private BorderPane bp;

    private SinglyLinkedList studentList;

    private StudentController studentController;

    public void setStudentController(StudentController controller) {
        this.studentController = controller;
    }

    @javafx.fxml.FXML
    public void initialize() {
        this.studentList = util.Utility.getStudentList();
    }

    @javafx.fxml.FXML
    public void cleanSortedStudentOnAction(ActionEvent actionEvent) {

        textFieldName.clear();
        textFieldStudentId.clear();
        textFieldAge.clear();
        textFieldAddress.clear();

    }

    @javafx.fxml.FXML
    public void addSortedStudentOnAction(ActionEvent actionEvent) {
        // Obtener los valores de los campos
        String id = textFieldStudentId.getText().trim();
        String name = textFieldName.getText().trim();
        String ageText = textFieldAge.getText().trim();
        String address = textFieldAddress.getText().trim();

        // Validar la entrada
        if (id.isEmpty() || name.isEmpty() || ageText.isEmpty() || address.isEmpty()) {
            util.FXUtility.alert("Error", "Todos los campos son obligatorios.").showAndWait();
            return;
        }

        try {
            int age = Integer.parseInt(ageText); // Convertir la edad a entero

            // Crear un nuevo estudiante
            Student student = new Student(id, name, age, address);

            // Agregarlo a la lista (esto asume que tienes el método addFirst)
            studentList.addInSortedList(student);
            util.Utility.setStudentList(studentList);

            // Mostrar un mensaje de éxito
            util.FXUtility.alert("Success", "Student added correctly.").showAndWait();

            if (studentController != null) {
                studentController.updateTableView();
            }

        } catch (NumberFormatException | ListException e) {
            util.FXUtility.alert("ERROR", "Age could be a valid number.").showAndWait();
        }


    }

    @javafx.fxml.FXML
    public void closeSortedStudentOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "student.fxml", bp);

    }
}