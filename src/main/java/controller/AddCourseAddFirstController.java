package controller;

import domain.DoublyLinkedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddCourseAddFirstController
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
        alert = util.FXUtility.alert("Course List", "Add Course (Add First)");
    }

    @javafx.fxml.FXML
    public void closeCourseOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "course.fxml", bp);

    }

    @javafx.fxml.FXML
    public void cleanCourseOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
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