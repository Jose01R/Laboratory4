package controller;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class StudentController
{
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TableView<Student> studentTableview; //establezco el tipo para el tableview
    @javafx.fxml.FXML
    private TableColumn<Student, String> idTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Student, String> nameTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Student, Integer> ageTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Student, String> addressTableColumn;

    //defino la lista enlazada interna
    private SinglyLinkedList studentList;
    private Alert alert; //para el manejo de alertas

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.studentList = util.Utility.getStudentList();
        alert = util.FXUtility.alert("Student List", "Display Student");
        alert.setAlertType(Alert.AlertType.ERROR);
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        try{
            if(studentList!=null && !studentList.isEmpty()){
                for(int i=1; i<=studentList.size(); i++) {
                    studentTableview.getItems().add((Student) studentList.getNode(i).data);
                }
            }
            //this.studentTableView.setItems(observableList);
        }catch(ListException ex){
            alert.setContentText("Student list is empty");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        this.studentList.clear();
        util.Utility.setStudentList(this.studentList); //actualizo la lista general
        this.alert.setContentText("The list was deleted");
        this.alert.setAlertType(Alert.AlertType.INFORMATION);
        this.alert.showAndWait();
        try {
            updateTableView(); //actualiza el contenido del tableview
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "containsStudent.fxml", bp);
    }

    @javafx.fxml.FXML
    public void sizeOnAction(ActionEvent actionEvent) {
        try {
            this.alert.setContentText("The number of students is : " + this.studentList.size());
            util.Utility.setStudentList(this.studentList); //actualizo la lista general
            this.alert.setAlertType(Alert.AlertType.INFORMATION);
            this.alert.showAndWait();
            updateTableView();//actualiza el contenido del tableview
            //disableButtonsIfListEmpty();
        } catch (ListException e) {
            alert.setHeaderText("Error: " + e.getMessage());
            alert.show();
        }
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "addStudent.fxml", bp);
    }


    @javafx.fxml.FXML
    public void addFirstOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "addStudentAddFirst.fxml", bp);
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "removeStudent.fxml", bp);
    }

    @javafx.fxml.FXML
    public void addSortedOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "addSortedStudent.fxml", bp);
    }

    @javafx.fxml.FXML
    public void getFirstOnAction(ActionEvent actionEvent) {
        //AGREGAR FORMATEO PARA SALIDA DEL ELEMENTO

        try {
            this.alert.setContentText("The first element is: " + this.studentList.getFirst());
            util.Utility.setStudentList(this.studentList); //actualizo la lista general
            this.alert.setAlertType(Alert.AlertType.INFORMATION);
            this.alert.showAndWait();
            updateTableView(); //actualiza el contenido del tableview
            //disableButtonsIfListEmpty();
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {
        try {
            this.studentList.removeFirst();
            util.Utility.setStudentList(this.studentList); //actualizo la lista general
            this.alert.setContentText("The first element was deleted");
            this.alert.setAlertType(Alert.AlertType.INFORMATION);
            this.alert.showAndWait();
            updateTableView(); //actualiza el contenido del tableview
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void getLastOnAction(ActionEvent actionEvent) {
        try {
            this.alert.setContentText("The last element is: " + this.studentList.getLast());
            util.Utility.setStudentList(this.studentList); //actualizo la lista general
            this.alert.setAlertType(Alert.AlertType.INFORMATION);
            this.alert.showAndWait();
            updateTableView();//actualiza el contenido del tableview
            //disableButtonsIfListEmpty();
        } catch (ListException e) {
            alert.setHeaderText("Error : " + e.getMessage());
            alert.show();
        }
    }

    @javafx.fxml.FXML
    public void sortOnAction(ActionEvent actionEvent) {
        try {
            this.studentList.sort();
            this.alert.setContentText("ORDERED LIST");
            util.Utility.setStudentList(this.studentList); //actualizo la lista general
            this.alert.setAlertType(Alert.AlertType.INFORMATION);
            this.alert.showAndWait();
            updateTableView();//actualiza el contenido del tableview
            //disableButtonsIfListEmpty();
        } catch (ListException e) {
            alert.setHeaderText("Error : " + e.getMessage());
            alert.show();
        }
    }

    public void updateTableView() throws ListException {
        this.studentTableview.getItems().clear(); //clear table
        this.studentList = util.Utility.getStudentList(); //cargo la lista
        if(studentList!=null && !studentList.isEmpty()){
            for(int i=1; i<=studentList.size(); i++) {
                this.studentTableview.getItems().add((Student)studentList.getNode(i).data);
            }
        }
    }

}