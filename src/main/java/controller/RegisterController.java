package controller;

import domain.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RegisterController {
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
    private DoublyLinkedList registerList;
    private Alert alert;

    @javafx.fxml.FXML
    public void initialize() {
        this.registerList = util.Utility.getRegisterList();
        registerObservableList = FXCollections.observableArrayList();

        // Configuración de las columnas del TableView
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Asignar el valor de Student ID y Name
        idStudentTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStudentId())  // Verifica que Register tenga getStudentId()
        );

        nameStudentTableColumn.setCellValueFactory(cellData -> {
            String studentId = cellData.getValue().getStudentId();
            String studentName = null; // Método corregido
            try {
                studentName = getStudentNameById(studentId);
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            return new SimpleStringProperty(studentName);
        });

        // Asignar el valor de Course ID y Name
        idCourseTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCourseId()) // Verifica que Register tenga getCourseId()
        );

        nameCourseTableColumn.setCellValueFactory(cellData -> {
            String courseId = cellData.getValue().getCourseId();
            String courseName = null; // Método corregido
            try {
                courseName = getCourseNameById(courseId);
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            return new SimpleStringProperty(courseName);
        });

        creditsTableColumn.setCellValueFactory(cellData -> {
            int credits = 0; // Método corregido
            try {
                credits = getCreditsByCourseId(cellData.getValue().getCourseId());
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            return new SimpleIntegerProperty(credits).asObject();
        });

        // Configuración de la columna de fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getRegisterDate().format(formatter))
        );

        // Cargar los registros en la tabla
        try {
            if (registerList != null && !registerList.isEmpty()) {
                for (int i = 1; i <= registerList.size(); i++) {
                    registerObservableList.add((Register) registerList.getNode(i).data);
                }
            }
            registrationTableview.setItems(registerObservableList);
        } catch (ListException ex) {
            showError("Error", "La lista de registros está vacía.");
        }
    }

    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "addRegister.fxml", bp);
    }

    @javafx.fxml.FXML
    public void addFirstOnAction(ActionEvent actionEvent) {}

    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {}

    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {}

    @javafx.fxml.FXML
    public void addSortedOnAction(ActionEvent actionEvent) {}

    @javafx.fxml.FXML
    public void getFirstOnAction(ActionEvent actionEvent) {}

    @javafx.fxml.FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {}

    @javafx.fxml.FXML
    public void getLastOnAction(ActionEvent actionEvent) {}

    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "containsRegistration.fxml", bp);
    }

    @javafx.fxml.FXML
    public void sortOnAction(ActionEvent actionEvent) {}

    @javafx.fxml.FXML
    public void sizeOnAction(ActionEvent actionEvent) {
        try {
            util.FXUtility.alert("MESSAGE", "The number of registers is: " + this.registerList.size());
            util.Utility.setRegisterList(this.registerList); // Update the global list
            updateTableView(); // Update the content of the TableView
        } catch (ListException e) {
            util.FXUtility.alert("ERROR", "ERROR OCURRED");
        }
    }

    // Método para actualizar la lista de registros
    public void updateTableView() throws ListException {
        this.registrationTableview.getItems().clear(); //clear table
        this.registerList = util.Utility.getRegisterList(); //cargo la lista
        if(registerList != null && !registerList.isEmpty()){
            for(int i = 1; i <= registerList.size(); i++) {
                this.registrationTableview.getItems().add((Register) registerList.getNode(i).data);
            }
        }
    }

    // Métodos para obtener elementos

    // Obtener el nombre del estudiante por ID
    private String getStudentNameById(String studentId) throws ListException {
        if (studentList == null || studentList.isEmpty()) {
            return "Desconocido"; // Retorna "Desconocido" si la lista está vacía
        }
        for (int i = 1; i <= studentList.size(); i++) {
            Student student = (Student) studentList.getNode(i).data;
            if (student.getId().equals(studentId)) {
                return student.getName();
            }
        }
        return "Desconocido"; // Si no se encuentra el estudiante, devuelve "Desconocido"
    }

    private String getCourseNameById(String courseId) throws ListException {
        if (courseList == null || courseList.isEmpty()) {
            return "Desconocido"; // Retorna "Desconocido" si la lista está vacía
        }
        for (int i = 1; i <= courseList.size(); i++) {
            Course course = (Course) courseList.getNode(i).data;
            if (course.getId().equals(courseId)) {
                return course.getName();
            }
        }
        return "Desconocido"; // Si no se encuentra el curso, devuelve "Desconocido"
    }

    private int getCreditsByCourseId(String courseId) throws ListException {
        if (courseList == null || courseList.isEmpty()) {
            return 0; // Retorna 0 si la lista está vacía
        }
        for (int i = 1; i <= courseList.size(); i++) {
            Course course = (Course) courseList.getNode(i).data;
            if (course.getId().equals(courseId)) {
                return course.getCredits();
            }
        }
        return 0; // Si no se encuentra el curso, devuelve 0
    }
    // Método para mostrar alertas de error
    private void showError(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
