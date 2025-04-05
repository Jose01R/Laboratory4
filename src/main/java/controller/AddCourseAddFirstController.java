package controller;

import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddCourseAddFirstController
{
    //defino la lista enlazada interna
    private DoublyLinkedList courseList;
    private Alert alert; //para el manejo de alertas
    private CourseController courseController;

    @javafx.fxml.FXML
    private TextField textFieldName;
    @javafx.fxml.FXML
    private TextField textFieldCourseId;
    @javafx.fxml.FXML
    private TextField textFieldCredits;
    @javafx.fxml.FXML
    private BorderPane bp;

    public void setCourseController(CourseController controller) {
        this.courseController = controller;
    }

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.courseList = util.Utility.getCourseList();
        alert = util.FXUtility.alert("Course List", "Add Course (Add First)");
    }

    @javafx.fxml.FXML
    public void closeCourseOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "course.fxml", bp);

    }

    @javafx.fxml.FXML
    public void cleanCourseOnAction(ActionEvent actionEvent) {
        textFieldName.clear();
        textFieldCourseId.clear();
        textFieldCredits.clear();
    }

    @javafx.fxml.FXML
    public void addCourseOnAction(ActionEvent actionEvent) {
        // Obtener los valores de los campos
        String id = textFieldCourseId.getText().trim();
        String name = textFieldName.getText().trim();
        String creditsText = textFieldCredits.getText().trim();

        // Validar la entrada
        if (id.isEmpty() || name.isEmpty() || creditsText.isEmpty()) {
            util.FXUtility.alert("Error", "Fields are necessary.");
            return;
        }

        try {
            int credits = Integer.parseInt(creditsText); // Convertir la edad a entero

            // Crear un nuevo estudiante
            Course course = new Course(id, name, credits);

            // Agregarlo a la lista (esto asume que tienes el método addFirst)
            courseList.addFirst(course);
            util.Utility.setCourseList(courseList);

            // Mostrar un mensaje de éxito
            util.FXUtility.alert("Success", "Course added correctly").showAndWait();

            if (courseController != null) {
                courseController.updateTableView();
            }

        } catch (NumberFormatException | ListException e) {
            util.FXUtility.alert("ERROR", "Credits could be a valid number.");
        }
    }

}