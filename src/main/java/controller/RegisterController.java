package controller;

import domain.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.time.format.DateTimeFormatter;

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
        this.courseList=util.Utility.getCourseList();
        this.studentList=util.Utility.getStudentList();
        registerObservableList = FXCollections.observableArrayList();
        alert = util.FXUtility.alert("Student List", "Display Student");
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
            showError("Error", "The register list is empty.");
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
    public void sortByStudentOnAction(ActionEvent actionEvent) {
        try {
            // Ordenar la lista de registros
            registerList.sort();  // Llamada al método sort

            // Actualizar la lista en el TableView
            registerObservableList.clear();
            for (int i = 1; i <= registerList.size(); i++) {
                registerObservableList.add((Register) registerList.getNode(i).data);
            }

            // Actualizar la visualización del TableView
            registrationTableview.setItems(registerObservableList);

            // Mostrar una alerta de éxito
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("List sorted by student's name");
            alert.showAndWait();
        } catch (ListException e) {
            showError("Error", "The register list is empty");
        }
    }



    @javafx.fxml.FXML
    public void getFirstOnAction(ActionEvent actionEvent) {}

    @javafx.fxml.FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {}

    @javafx.fxml.FXML
    public void getNextOnAction(ActionEvent actionEvent){
        try {

            // Asegurarse de que la alerta esté inicializada
            if (alert == null) {
                alert = new Alert(Alert.AlertType.INFORMATION);
            }

            // Obtener el registro seleccionado
            Register selectedRegister = registrationTableview.getSelectionModel().getSelectedItem();

            if (selectedRegister != null) {
                // Debug: Verificar el tipo del registro seleccionado
                System.out.println("Selected Register: " + selectedRegister);

                // Llamar al método getPrev pasando el registro seleccionado
                Object nextRegister = this.registerList.getNext(selectedRegister);

                // Verificar si el registro previo fue encontrado
                if (nextRegister != null) {
                    System.out.println("next Register: " + nextRegister);
                    alert.setContentText("next element: " + nextRegister.toString());
                } else {
                    System.out.println("next element found.");
                    alert.setContentText("next element not found.");
                }
            } else {
                System.out.println("No register selected.");
                alert.setContentText("No register selected.");
            }

            // Mostrar la alerta
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.showAndWait();

            // Actualizar la tabla después de la operación
            updateTableView();

        } catch (ListException e) {
            showError("Error", "Failed to retrieve the next element.");
        }

    }

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

    //Métodos para obtener elementos

    //Obtener el nombre del estudiante por ID
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
        return "Desconocido"; //Si no se encuentra el estudiante, devuelve "Desconocido"
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
