package controller;

import domain.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.Date;

public class AddRegisterController {


    @javafx.fxml.FXML
    private ComboBox comboBoxStudent;
    @javafx.fxml.FXML
    private TextField textFieldRegisterId;
    @javafx.fxml.FXML
    private DatePicker datePickerRegister;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private ComboBox conboBoxCourse;
    @javafx.fxml.FXML
    private TableView<Student> registrationTableview; //establezco el tipo para el tableview

    @javafx.fxml.FXML
    private TableColumn<Student, String> idTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Register, Date> dateTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Student, String> idStudentTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Student, String> nameStudentTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Course, String> idCourseTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Course, String> nameCourseTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Course, String> creditsCourseTableColumn;

    private SinglyLinkedList studentList;
    private DoublyLinkedList courseList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.studentList = util.Utility.getStudentList();
        alert = util.FXUtility.alert("Registration List", "Display Student");
        alert.setAlertType(Alert.AlertType.ERROR);

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        idStudentTableColumn.setCellValueFactory(new PropertyValueFactory<>("Student Id"));
        nameStudentTableColumn.setCellValueFactory(new PropertyValueFactory<>("Student Name"));
        idCourseTableColumn.setCellValueFactory(new PropertyValueFactory<>("Course Id"));
        nameCourseTableColumn.setCellValueFactory(new PropertyValueFactory<>("Course Name"));
        creditsCourseTableColumn.setCellValueFactory(new PropertyValueFactory<>("Credits"));

        try{
            if(studentList!=null && !studentList.isEmpty() && courseList!=null && !courseList.isEmpty()){

                for(int i=1; i<=studentList.size(); i++) {
                    registrationTableview.getItems().add((Student) studentList.getNode(i).data);
                }
                for(int i=1; i<=courseList.size(); i++) {
                  //  registrationTableview.getItems().add((Course) courseList.getNode(i).data);
                }

            }
            //this.studentTableView.setItems(observableList);
        }catch(ListException ex){
            alert.setContentText("Student list is empty");
            alert.showAndWait();
        }

    }

        @javafx.fxml.FXML
    public void cleanSortedStudentOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addSortedStudentOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void closeSortedStudentOnAction(ActionEvent actionEvent) {
    }



    public void updateTableView() throws ListException {
        this.registrationTableview.getItems().clear(); //clear table
        this.studentList = util.Utility.getStudentList(); //cargo la lista
        if(studentList!=null && !studentList.isEmpty()){
            for(int i=1; i<=studentList.size(); i++) {
                this.registrationTableview.getItems().add((Student)studentList.getNode(i).data);
            }
        }
    }



}
