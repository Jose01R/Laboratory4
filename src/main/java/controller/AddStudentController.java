package controller;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class AddStudentController
{
    @javafx.fxml.FXML
    private TextField textFieldName;
    @javafx.fxml.FXML
    private TextField textFieldId;
    @javafx.fxml.FXML
    private TextField textFieldAge;
    @javafx.fxml.FXML
    private TextField textFieldAddress;
    @javafx.fxml.FXML
    private BorderPane bp;
    //defino la lista enlazada interna
    private SinglyLinkedList studentList;
    private Alert alert; //para el manejo de alertas
    private Student student;

    @javafx.fxml.FXML
    public void initialize() {
        //cargamos la lista general
        this.studentList = util.Utility.getStudentList();
        alert = util.FXUtility.alert("Student List", "Add Student");

    }

   @javafx.fxml.FXML
   void addOnAction(ActionEvent event) {

       String id= textFieldId.getText().trim();
       String name=textFieldName.getText().trim();
       String ageText=(textFieldAge.getText().trim());
       String address= textFieldAddress.getText().trim();
       // Validar la entrada
       if (id.isEmpty() || name.isEmpty() || ageText.isEmpty() || address.isEmpty()) {
           util.FXUtility.alert("Error", "Todos los campos son obligatorios.").showAndWait();
           return;
       }
       if (idAlreadyExists(id)) {
           alert.setAlertType(Alert.AlertType.WARNING);
           alert.setHeaderText("Ya existe un estudiante con ese ID.");
           alert.show();
           textFieldId.clear();
           return;
       }

       int age=Integer.parseInt(textFieldAge.getText().trim());
       student= new Student(id,name,age,address);
       studentList.add(student);
       //Alerta al añadir
       alert.setAlertType(Alert.AlertType.INFORMATION);
       alert.setTitle("Add student");
       alert.setHeaderText("The student has been added");
       //Limpiar una vez se agregan
       textFieldAge.clear();
       textFieldName.clear();
       textFieldId.clear();
       textFieldAddress.clear();
   }

    @javafx.fxml.FXML
    void ageOnKeyType(KeyEvent event) {
        String character = event.getCharacter();
        // Si no es un dígito, no se permite escribir
        if (!character.matches("\\d")) {
            event.consume(); // Cancela el evento, no se escribe el caracter
            alert.setTitle("Error");
            alert.setHeaderText("Debe ser un valor númerico");
            alert.show();
            textFieldAge.clear();
        }
    }

    @javafx.fxml.FXML
    void cleanOnAction(ActionEvent event) {
        textFieldAge.clear();
        textFieldName.clear();
        textFieldId.clear();
        textFieldAddress.clear();
    }

    @javafx.fxml.FXML
    void closeOnAction(ActionEvent event) {
        util.FXUtility.loadPage("ucr.lab.HelloApplication", "student.fxml", bp);
    }

    public boolean idAlreadyExists(String id) {
        try {
            for (int i = 1; i <= studentList.size(); i++) {
                Student s = (Student) studentList.getNode(i).data;
                if (s.getId().equals(id)) {
                    return true; //El iD ya existe
                }
            }
        } catch (ListException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al validar ID: " + e.getMessage());
            alert.show();
        }
        return false; //El iD no existe
    }


}