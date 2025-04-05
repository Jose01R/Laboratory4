package controller;

import domain.*;
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
    ;
    private Alert alert;

    @javafx.fxml.FXML
    public void initialize() {
        registerObservableList = FXCollections.observableArrayList();
        // Configuración de las columnas del TableView
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        // Asignar el valor de Student ID y Name
        idStudentTableColumn.setCellValueFactory(cellData -> {
            String studentId = cellData.getValue().getStudentId();
            return new SimpleStringProperty(studentId);
        });

        nameStudentTableColumn.setCellValueFactory(cellData -> {
            String studentId = cellData.getValue().getStudentId();
            String studentName;
            try {
                studentName = getStudentNameById(studentId);
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            return new SimpleStringProperty(studentName);
        });

        // Asignar el valor de Course ID y Name
        idCourseTableColumn.setCellValueFactory(cellData -> {
            String courseId = cellData.getValue().getCourseId();
            return new SimpleStringProperty(courseId);
        });

        nameCourseTableColumn.setCellValueFactory(cellData -> {
            String courseId = cellData.getValue().getCourseId();
            String courseName = null;
            try {
                courseName = getCourseNameById(courseId);
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            return new SimpleStringProperty(courseName);
        });

        creditsTableColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleIntegerProperty(getCreditsByCourseId(cellData.getValue().getCourseId())).asObject();
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
        });

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getRegisterDate().format(formatter))
        );

        try {
            if (registerList != null && !registerList.isEmpty()) {
                for (int i = 1; i <= courseList.size(); i++) {
                    registrationTableview.getItems().add((Register) registerList.getNode(i).data);
                }
            }
            //this.studentTableView.setItems(observableList);
        } catch (ListException ex) {
            alert.setContentText("Course list is empty");
            alert.showAndWait();
        }


    }


    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "addRegister.fxml", bp);
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

    /// ////////////////////////////////////////////

    // Método para actualizar la lista de registros
    public void updateRegisterList(DoublyLinkedList updatedRegisterList) {
        this.registerList = updatedRegisterList;

        // Depuración: Imprimir los registros para verificar que se están recibiendo correctamente
        System.out.println("Actualizando la lista de registros...");
        printRegisterList();

        // Aquí podrías actualizar la TableView o cualquier otro componente que use registerList
        ObservableList<Register> observableRegisters = FXCollections.observableArrayList();

        try {
            for (int i = 1; i <= registerList.size(); i++) {
                Register register = (Register) registerList.getNode(i).data;
                observableRegisters.add(register);
            }
            registrationTableview.setItems(observableRegisters);
        } catch (ListException e) {
            util.FXUtility.alert("ERROR", "No se pudo actualizar la lista de registros");
        }
    }

    private void printRegisterList() {
        try {
            // Recorremos la lista de registros y los imprimimos
            for (int i = 1; i <= registerList.size(); i++) {
                Register register = (Register) registerList.getNode(i).data;
                System.out.println("Registro " + i + ": ID = " + register.getId() +
                        ", Fecha = " + register.getRegisterDate() +
                        ", Student ID = " + register.getStudentId() +
                        ", Course ID = " + register.getCourseId());
            }
        } catch (ListException e) {
            System.out.println("Error al acceder a la lista de registros: " + e.getMessage());
        }
    }


    /// ///////////////METODOS PARA OBTENER ELEMENTOS//////////////////////


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



}
