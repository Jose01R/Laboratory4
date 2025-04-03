package controller;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddStudentController
{
    @javafx.fxml.FXML
    private TextField textFieldName;
    @javafx.fxml.FXML
    private TextField textFieldID;
    @javafx.fxml.FXML
    private TextField textFieldAge;
    @javafx.fxml.FXML
    private TextField textAreaAddress;
    @javafx.fxml.FXML
    private BorderPane bp;
    //defino la lista enlazada interna
    private SinglyLinkedList studentList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.studentList = util.Utility.getStudentList();
        alert = util.FXUtility.alert("Student List", "Add Student");
    }

    @javafx.fxml.FXML
    public void onKeyTypeAgeValidation(Event event) {
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void cleanOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "student.fxml", bp);
    }
}