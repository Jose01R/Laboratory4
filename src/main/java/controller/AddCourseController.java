package controller;

import domain.Course;
import domain.DoublyLinkedList;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class AddCourseController
{
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField textFieldName;
    @javafx.fxml.FXML
    private TextField textFieldCourseId;
    @javafx.fxml.FXML
    private TextField textFieldCredits;

    //defino la lista enlazada interna
    private DoublyLinkedList courseList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.courseList = util.Utility.getCourseList();
        alert = util.FXUtility.alert("Course List", "Add Course");
    }

    @javafx.fxml.FXML
    public void closeCourseOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "course.fxml", bp);
    }

    @javafx.fxml.FXML
    public void cleanCourseOnAction(ActionEvent actionEvent) {
        textFieldCourseId.clear();
        textFieldCredits.clear();
        textFieldName.clear();
    }

    @javafx.fxml.FXML
    public void addCourseOnAction(ActionEvent actionEvent) {
        String id = textFieldCourseId.getText().trim();
        String name = textFieldName.getText().trim();
        int credits = Integer.parseInt(textFieldCredits.getText().trim());

        Course course= new Course(id, name, credits);

        courseList.add(course);
        //Alerta al añadir
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Add course");
        alert.setHeaderText("The course has been added");
        alert.showAndWait();

        //Limpiar una vez se agregan
        textFieldCourseId.clear();
        textFieldCredits.clear();
        textFieldName.clear();
    }

    @javafx.fxml.FXML
    void creditsOnKeyType(KeyEvent event) {
        String character = event.getCharacter();
        // Si no es un dígito, no se permite escribir
        if (!character.matches("\\d")) {
            event.consume(); // Cancela el evento, no se escribe el caracter
            alert.setTitle("Error");
            alert.setHeaderText("Debe ser un valor númerico");
            alert.show();
            textFieldCredits.clear();
        }
    }

}