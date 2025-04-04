package controller;

import domain.DoublyLinkedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddCourseAddFirstController
{
    @javafx.fxml.FXML
    private TextField textFieldName;

    //defino la lista enlazada interna
    private DoublyLinkedList courseList;
    private Alert alert; //para el manejo de alertas
    @javafx.fxml.FXML
    private TextField textFieldStudentId;
    @javafx.fxml.FXML
    private TextField textFieldAge;
    @javafx.fxml.FXML
    private TextField textFieldAddress;

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.courseList = util.Utility.getCourseList();
        alert = util.FXUtility.alert("Course List", "Add Course (Add First)");
    }

    @Deprecated
    public void closeCourseOnAction(ActionEvent actionEvent) {
        //util.FXUtility.loadPage("ucr.lab.HelloApplication", "course.fxml", bp);

    }

    @Deprecated
    public void cleanCourseOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void addCourseOnAction(ActionEvent actionEvent) {
    }


//
//    @javafx.fxml.FXML
//    public void onKeyTypeAgeValidation(Event event) {
//    }
//
//    @javafx.fxml.FXML
//    public void addOnAction(ActionEvent actionEvent) {
//    }
//
//    @javafx.fxml.FXML
//    public void cleanOnAction(ActionEvent actionEvent) {
//    }
//
//    @javafx.fxml.FXML
//    public void closeOnAction(ActionEvent actionEvent) {
//        util.FXUtility.loadPage("ucr.lab.HelloApplication", "student.fxml", bp);
//    }
}