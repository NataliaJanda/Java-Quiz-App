package com.example.javaquizzapp.FXcontroller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import com.example.javaquizzapp.entity.Roles;
import com.example.javaquizzapp.entity.Student;
import com.example.javaquizzapp.service.StudentService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;

@Controller
public class GuiController {
    private final StudentService studentService;
    public GuiController(StudentService studentService) {
        this.studentService = studentService;
    }
    public PasswordField passwordField;
    public PasswordField repeatPasswordField;
    public TextField lastNameField;
    public TextField nameField;
    public TextField indexField;
    public Button Register, RegisterPanel, LoginPanel;
    @Autowired
    @Lazy
    public testController testController;

    public void RegisterSubmit(javafx.event.ActionEvent actionEvent) {
        String index = indexField.getText();
        String password = passwordField.getText();
        String repeatPassword = repeatPasswordField.getText();
        String name = nameField.getText();
        String lastName = lastNameField.getText();
        Roles roles = Roles.STUDENT;
        if (!password.equals(repeatPassword)) {
            System.out.println("Passwords do not match!");
            return;
        }

        Student student = new Student(null, index, name, lastName, password,roles);
        studentService.registerStudent(student);
        System.out.println("Student registered successfully!");
        try {
            Stage stage = (Stage) Register.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/startQuiz.fxml"));
            fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void RegisterButtonPanel(javafx.event.ActionEvent actionEvent){
        try {
            Stage stage = (Stage) Register.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui.fxml"));
            fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoginButtonPanel(javafx.event.ActionEvent actionEvent){
        try {
            Stage stage = (Stage) Register.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
            fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
