package controller;

import domain.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RegisterController
{
    @javafx.fxml.FXML
    private BorderPane bp;

    @javafx.fxml.FXML
    private TableView<Register> registrationTableview;


    @javafx.fxml.FXML
    private Button getFirstButton;
    @javafx.fxml.FXML
    private Button sizeButton;
    @javafx.fxml.FXML
    private Button containsButton;
    @javafx.fxml.FXML
    private Button getLastButton;
    @javafx.fxml.FXML
    private Button removeFirstButton;
    @javafx.fxml.FXML
    private Button removeButton;
    @javafx.fxml.FXML
    private Button sortButton;

    @javafx.fxml.FXML
    private TableColumn<Register, Integer> idTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Register, String> dateTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Register, String> idStudentTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Register, String> nameStudentTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Register, String> idCourseTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Register, String> nameCourseTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Register, Integer> creditsTableColumn;

    private ObservableList<Register> registerObservableList;
    private DoublyLinkedList courseList;
    private SinglyLinkedList studentList;


    @javafx.fxml.FXML
    public void initialize() {
        registerObservableList = FXCollections.observableArrayList();
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Formateo para la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getRegisterDate().format(formatter))
        );

        // Obtener el ID del estudiante desde Register
        idStudentTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentId()));
        nameStudentTableColumn.setCellValueFactory(cellData -> {
            String studentName = null;
            try {
                studentName = getStudentNameById(cellData.getValue().getStudentId());
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            return new SimpleStringProperty(studentName);
        });

        // Obtener el ID del curso desde Register
        idCourseTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseId()));
        nameCourseTableColumn.setCellValueFactory(cellData -> {
            String courseName = null;
            try {
                courseName = getCourseNameById(cellData.getValue().getCourseId());
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            return new SimpleStringProperty(courseName);
        });

        creditsTableColumn.setCellValueFactory(cellData -> {
            int credits = 0;
            try {
                credits = getCreditsByCourseId(cellData.getValue().getCourseId());
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            return new SimpleIntegerProperty(credits).asObject();  // Correcto para columnas Integer
        });

        registrationTableview.setItems(registerObservableList);
    }

    // Método para agregar un registro al TableView
    public void addRegister(Register register) {
        registerObservableList.add(register);
        registrationTableview.setItems(registerObservableList);
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "addRegister.fxml", bp);
    }

    // Obtener el nombre del estudiante por ID
    private String getStudentNameById(String studentId) throws ListException {
        if (studentList == null || studentList.isEmpty()) {
            showError("Error", "La lista de estudiantes está vacía.");
            return "Desconocido";
        }
        for (int i = 1; i <= studentList.size(); i++) {
            Student student = (Student) studentList.getNode(i).data;
            if (student.getId().equals(studentId)) {
                return student.getName();
            }
        }
        showError("Error", "Estudiante no encontrado.");
        return "Desconocido";
    }

    // Obtener el nombre del curso por ID
    private String getCourseNameById(String courseId) throws ListException {
        if (courseList == null || courseList.isEmpty()) {
            showError("Error", "La lista de cursos está vacía.");
            return "Desconocido";
        }
        for (int i = 1; i <= courseList.size(); i++) {
            Course course = (Course) courseList.getNode(i).data;
            if (course.getId().equals(courseId)) {
                return course.getName();
            }
        }
        showError("Error", "Curso no encontrado.");
        return "Desconocido";
    }

    // Obtener los créditos de un curso por ID
    private int getCreditsByCourseId(String courseId) throws ListException {
        if (courseList == null || courseList.isEmpty()) {
            showError("Error", "La lista de cursos está vacía.");
            return 0;
        }
        for (int i = 1; i <= courseList.size(); i++) {
            Course course = (Course) courseList.getNode(i).data;
            if (course.getId().equals(courseId)) {
                return course.getCredits();
            }
        }
        showError("Error", "Curso no encontrado.");
        return 0;
    }

    // Método para mostrar alertas de error
    private void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void addFirstOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addSortedOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void getFirstOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void getLastOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void sortOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void sizeOnAction(ActionEvent actionEvent) {
    }
}
