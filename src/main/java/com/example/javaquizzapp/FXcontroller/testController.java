package com.example.javaquizzapp.FXcontroller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import com.example.javaquizzapp.entity.Answer;
import com.example.javaquizzapp.entity.Question;
import com.example.javaquizzapp.service.QuestionService;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class testController implements Initializable {
    @Autowired
    private QuestionService questionService;
    public Label LabelQuestionNumber;
    public CheckBox Answer1;
    public CheckBox Answer2;
    public CheckBox Answer3;
    public CheckBox Answer4;
    public Label Question;
    public Button AboutQuiz;
    public Button UserTestScore;
    public Button Back;
    private List<com.example.javaquizzapp.entity.Question> questions;
    private int currentQuestionIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        questions = questionService.getAllQuestionsWithAnswers();
        if (!questions.isEmpty()) {
            displayQuestion(questions.get(currentQuestionIndex));
        }
    }
    public void BackButton(javafx.event.ActionEvent actionEvent){
        try {
            Stage stage = (Stage) Back.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/startQuiz.fxml"));
            fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AboutQuizButton(javafx.event.ActionEvent actionEvent){}

    public void UserTestScoreButton(javafx.event.ActionEvent actionEvent){}

    public void NextButton(javafx.event.ActionEvent actionEvent){
        int i = Integer.parseInt(LabelQuestionNumber.getText());
        i++;
        LabelQuestionNumber.setText(String.valueOf(i));
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            displayQuestion(questions.get(currentQuestionIndex));
        }
    }
    private void displayQuestion(Question question) {
        LabelQuestionNumber.setText(String.valueOf(currentQuestionIndex + 1));
        Question.setText(question.getQuestion());

        List<Answer> answers = question.getAnswers();
        if (!answers.isEmpty()) {
            if (answers.size() > 0) Answer1.setText(answers.get(0).getAnswer());
            if (answers.size() > 1) Answer2.setText(answers.get(1).getAnswer());
            if (answers.size() > 2) Answer3.setText(answers.get(2).getAnswer());
            if (answers.size() > 3) Answer4.setText(answers.get(3).getAnswer());
        } else {
            // Możesz ustawić puste teksty, jeśli lista odpowiedzi jest pusta
            Answer1.setText("");
            Answer2.setText("");
            Answer3.setText("");
            Answer4.setText("");
        }

        // Resetowanie zaznaczeń odpowiedzi
        Answer1.setSelected(false);
        Answer2.setSelected(false);
        Answer3.setSelected(false);
        Answer4.setSelected(false);
    }
}
