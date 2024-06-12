package com.example.javaquizzapp.controller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import com.example.javaquizzapp.entity.Student;
import com.example.javaquizzapp.service.StudentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

@Controller
public class GuiController {
    private final StudentService studentService;

    public GuiController(StudentService studentService) {
        this.studentService = studentService;
    }
    public TextField passwordField;
    public TextField repeatPasswordField;
    public TextField lastNameField;
    public TextField nameField;
    public TextField indexField;
    public Button Register;

    @FXML
    private Label label1;

    public void RegisterSubmit(javafx.event.ActionEvent actionEvent) {
        String index = indexField.getText();
        String password = passwordField.getText();
        String repeatPassword = repeatPasswordField.getText();
        String name = nameField.getText();
        String lastName = lastNameField.getText();
//      System.out.println(index);
        if (!password.equals(repeatPassword)) {
            System.out.println("Passwords do not match!");
            return;
        }

        Student student = new Student(null, index, name, lastName, password);
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

}
