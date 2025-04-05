package controller;

import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class AddRegisterController {

    @javafx.fxml.FXML
    private BorderPane bp;

    @javafx.fxml.FXML
    private TextField textFieldRegisterId;
    @javafx.fxml.FXML
    private DatePicker datePickerRegister;
    @javafx.fxml.FXML
    private ComboBox comboBoxCourse;
    @javafx.fxml.FXML
    private ComboBox comboBoxStudent;

    private DoublyLinkedList courseList;
    private SinglyLinkedList studentList;
    private Student student;
    private Course course;
    private Alert alert; //para el manejo de alertas
    private LocalDateTime registerDate;

    private RegisterController registerController;  // Referencia al RegisterController
    private DoublyLinkedList registerList;

    public void setRegisterController(RegisterController controller) {
        this.registerController = controller;
    }

    public void setRegisterList(DoublyLinkedList registerList) {
        this.registerList = registerList;
    }

    @javafx.fxml.FXML
    public void initialize() {
        // Inicializar las listas
        this.studentList = util.Utility.getStudentList();
        this.courseList = util.Utility.getCourseList();
        this.registerList = util.Utility.getRegisterList()  ;

        alert = util.FXUtility.alert("Register List", "Add Register");

        // Llamamos a los métodos para cargar los ComboBoxes
        loadStudentComboBox();
        loadCourseComboBox();
    }

    //METODO PARA GUARDAR HORA Y FECHA
    @javafx.fxml.FXML
    private void onDateSelected(ActionEvent event) {
        LocalDate selectedDate = datePickerRegister.getValue();
        LocalTime currentTime = LocalTime.now();

        if (selectedDate != null) {
            registerDate = LocalDateTime.of(selectedDate, currentTime);
            //MENSAJE DEPURACION
            System.out.println("Fecha y hora guardadas: " + registerDate);
        }
    }

    @javafx.fxml.FXML
    public void addRegisterOnAction(ActionEvent actionEvent) {

        String id = textFieldRegisterId.getText().trim();
        Student selectedStudent = (Student) comboBoxStudent.getValue();
        Course selectedCourse = (Course) comboBoxCourse.getValue();

        // Verifica si todos los campos están completos
        if (id.isEmpty() || selectedStudent == null || selectedCourse == null || registerDate == null) {
            util.FXUtility.alert("ERROR", "Todos los campos deben ser completados.").showAndWait();
            return;
        }

        // Crear el nuevo registro
        Register register = new Register(
                Integer.parseInt(id),
                registerDate,
                selectedStudent.getId(),
                selectedCourse.getId()
        );

        // Agregar el nuevo registro a la lista local registerList
        registerList.add(register);

        if (registerController != null) {
            registerController.updateRegisterList(registerList);
        }

        // Limpiar los campos después de agregar el registro
        cleanRegisterOnAction(actionEvent);

        // Mostrar una alerta de éxito
        util.FXUtility.alert("Success", "Register added correctly").showAndWait();

    }

    @javafx.fxml.FXML
    public void cleanRegisterOnAction(ActionEvent actionEvent) {
        textFieldRegisterId.clear();
        datePickerRegister.setValue(null);
        comboBoxStudent.getSelectionModel().clearSelection();
        comboBoxCourse.getSelectionModel().clearSelection();
    }

    @javafx.fxml.FXML
    public void closeRegisterOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "register.fxml", bp);
    }

//    public boolean idAlreadyExists(String id) {
//        for (Register register : registerObservableList) {
//            if (String.valueOf(register.getId()).equals(id)) {
//                return true; // El ID del registro ya existe
//            }
//        }
//        return false;
//    }

    private void loadStudentComboBox() {
        ObservableList<Student> observableStudents = FXCollections.observableArrayList();

        try {
            if (studentList != null && !studentList.isEmpty()) {
                for (int i = 1; i <= studentList.size(); i++) {
                    Student student = (Student) studentList.getNode(i).data;
                    observableStudents.add(student);
                }
                comboBoxStudent.setItems(observableStudents);
            }
        } catch (ListException e) {
            util.FXUtility.alert("ERROR", "No se pudo cargar la lista de estudiantes");
        }
    }


    private void loadCourseComboBox() {
        ObservableList<Course> observableCourses = FXCollections.observableArrayList();

        try {
            if (courseList != null && !courseList.isEmpty()) {
                for (int i = 1; i <= courseList.size(); i++) {
                    Course course = (Course) courseList.getNode(i).data;
                    observableCourses.add(course);
                }
                comboBoxCourse.setItems(observableCourses);
            }
        } catch (ListException e) {
            util.FXUtility.alert("ERROR", "No se pudo cargar la lista de cursos");
        }
    }


}
