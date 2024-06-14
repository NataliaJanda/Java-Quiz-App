package com.example.javaquizzapp.FXcontroller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import  javafx.scene.control.Button;

import java.io.IOException;

@Controller
public class startQuizController {

    public Button start;

    public void startQuiz(javafx.event.ActionEvent actionEvent){
        try {
            Stage stage = (Stage) start.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/test.fxml"));
            fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

            testController quizController = fxmlLoader.getController();
            quizController.resetQuiz();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
