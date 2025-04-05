package controller;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class RemoveStudentController {

    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField textFieldName;
    @javafx.fxml.FXML
    private TextField textFieldId;
    private SinglyLinkedList studentList;
    private Alert alert; //para el manejo de alertas
    private Student student;

    @javafx.fxml.FXML
    public void initialize(){

        this.studentList = util.Utility.getStudentList();
        alert = util.FXUtility.alert("Student List", "Remove Student");


    }
    @javafx.fxml.FXML
    void clearOnAction(ActionEvent event) {
        textFieldId.clear();
        textFieldName.clear();
    }

    @javafx.fxml.FXML
    void closeOnAction(ActionEvent event) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "student.fxml", bp);
    }

    @javafx.fxml.FXML
    void removeOnAction(ActionEvent event) {
        String id = textFieldId.getText().trim();
        String name=textFieldName.getText().trim();
        try {
            for (int i = 1; i <= studentList.size(); i++) {
                Student student = (Student) studentList.getNode(i).data;
                if (util.Utility.compare(student.getId(),id)==0 && util.Utility.compare(student.getName(),name)==0) {
                    studentList.remove(student);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("The student has been deleted.");
                    alert.show();

                    break;//Se sale del metodo si elimina un estudiante
                }
                if(textFieldName.getText().isEmpty()||textFieldId.getText().isEmpty()) {
                    alert.setHeaderText("Both text fields have to be filled");
                    alert.show();
                }else
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setHeaderText("Student not found.");
                    alert.show();
            }

        } catch (ListException e) {
            alert.setHeaderText("Error al eliminar el estudiante: " + e.getMessage());
            alert.show();
        }
        textFieldId.clear();
        textFieldName.clear();
    }

}

