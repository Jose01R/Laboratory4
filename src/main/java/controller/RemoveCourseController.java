package controller;

import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class RemoveCourseController {

    @javafx.fxml.FXML
    private TextField idTextField;
    @javafx.fxml.FXML
    private BorderPane bp;

    private DoublyLinkedList courseList;
    private Alert alert; //para el manejo de alertas
    private Course course;

    @javafx.fxml.FXML
    public void initialize(){
        this.courseList = util.Utility.getCourseList();
        alert = util.FXUtility.alert("Course List", "Remove Course");
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        idTextField.clear();
    }

    @javafx.fxml.FXML
    public void closeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "course.fxml", bp);
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        String id = idTextField.getText().trim();
        try {
            for (int i = 1; i <= courseList.size(); i++) {
                Course course = (Course) courseList.getNode(i).data;
                if (util.Utility.compare(course.getId(),id)==0) {
                    courseList.remove(course);
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("The course has been deleted.");
                    alert.show();

                    break;//Se sale del metodo si elimina un estudiante
                }
                if(idTextField.getText().isEmpty()) {
                    alert.setHeaderText("Both text fields have to be filled");
                    alert.show();
                }else
                    alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText("Course not found.");
                alert.show();
            }

        } catch (ListException e) {
            alert.setHeaderText("Error al eliminar el curso: " + e.getMessage());
            alert.show();
        }
        idTextField.clear();
    }
}
