package com.example.javaquizzapp.FXcontroller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import com.example.javaquizzapp.entity.Student;
import com.example.javaquizzapp.repository.StudentRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import  javafx.scene.control.Button;

import java.io.IOException;
import java.util.Optional;

@Controller
public class startQuizController {

    public Button start;
    @Getter
    @Setter
    private Student currentStudent;
    private final StudentRepository studentRepository;

    public startQuizController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

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
    @FXML
    public void initialize() {
        if (currentStudent != null) {
            System.out.println("Zalogowany student: " + currentStudent.getIndex());
            Optional<Student> studentOptional = studentRepository.findByIndex(currentStudent.getIndex());

        }
    }

}
