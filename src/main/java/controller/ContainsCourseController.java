package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class ContainsCourseController {
    @javafx.fxml.FXML
    private TextField idTextField;
    @javafx.fxml.FXML
    private TextField nameCourseTextField;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField creditsTextField;

    private DoublyLinkedList courseList;
    private Alert alert; //para el manejo de alertas
    private Course course;

    @javafx.fxml.FXML
    public void initialize(){
        //cargamos la lista general
        this.courseList = util.Utility.getCourseList();
        alert = util.FXUtility.alert("Course List", "Add Course");

    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        nameCourseTextField.clear();
        idTextField.clear();
        creditsTextField.clear();
    }

    @javafx.fxml.FXML
    public void creditsOnKeyType(KeyEvent event) {
        String character = event.getCharacter();
        // Si no es un dígito, no se permite escribir
        if (!character.matches("\\d")) {
            event.consume(); // Cancela el evento, no se escribe el caracter
            alert.setTitle("Error");
            alert.setHeaderText("Debe ser un valor númerico");
            alert.show();
            creditsTextField.clear();
        }
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
        String id = idTextField.getText().trim();
        String name = nameCourseTextField.getText().trim();
        int credits = Integer.parseInt(creditsTextField.getText().trim());

        Course course= new Course(id,name,credits);
        try {
            if (courseList.contains(course)){
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("The course is on the list");
                alert.show();
            }else
                alert.setHeaderText("The course is not in the list");
            alert.show();

        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "course.fxml", bp);
    }
}
