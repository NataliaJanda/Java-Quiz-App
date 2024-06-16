package com.example.javaquizzapp.FXcontroller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import com.example.javaquizzapp.service.StudentService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller

public class LoginController {

    @Autowired
    private StudentService studentService;
    public Button RegisterF, LoginF, Login;
    public TextField indexLoginField;
    public PasswordField passwordLoginField;
    public Label errorMessage;

    public void LoginSubmit() {
        String index = indexLoginField.getText();
        String password = passwordLoginField.getText();

        if (studentService.validateStudent(index, password)) {
            try {
                Stage stage = (Stage) Login.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/startQuiz.fxml"));
                fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                errorMessage.setText("Wystąpił błąd podczas ładowania nowego widoku.");
            }
        } else {
            errorMessage.setText("Niepoprawny index lub hasło");
        }
    }

    public void RegisterButtonF(){
        try {
            Stage stage = (Stage) Login.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui.fxml"));
            fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoginButtonF(){
        try {
            Stage stage = (Stage) Login.getScene().getWindow();
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
