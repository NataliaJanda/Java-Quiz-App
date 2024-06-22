package com.example.javaquizzapp.FXcontroller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import com.example.javaquizzapp.entity.Student;
import com.example.javaquizzapp.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class UsersController {
    @Autowired
    private StudentService studentService;
    public Button Back;
    public TableColumn<Student, String> name;
    public TableColumn<Student, String> lastName;
    public TableColumn<Student, String> index;
    @FXML
    private TableView<Student> studentTable;

    @FXML
    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        index.setCellValueFactory(new PropertyValueFactory<>("index"));


        List<Student> students = studentService.findAllStudents();
        ObservableList<Student> studentObservableList = FXCollections.observableArrayList(students);
        studentTable.setItems(studentObservableList);
    }
    public void BackButton(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) Back.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdminGui.fxml"));
            fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
