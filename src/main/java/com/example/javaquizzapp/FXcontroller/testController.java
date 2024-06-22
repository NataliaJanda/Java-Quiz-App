package com.example.javaquizzapp.FXcontroller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import com.example.javaquizzapp.entity.*;
import com.example.javaquizzapp.service.CurrentStudentService;
import com.example.javaquizzapp.service.QuestionService;
import com.example.javaquizzapp.service.StudentService;
import com.example.javaquizzapp.service.TestService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class testController implements Initializable {
    @Autowired
    public QuestionService questionService;
    @Autowired
    @Lazy
    private StudentService studentService;
    @Autowired
    private CurrentStudentService currentStudentService;
    private final TestService testService;
    public Label LabelQuestionNumber, MaxScore, YourScore, Grade,Question, Time;
    public CheckBox Answer1, Answer2, Answer3, Answer4;
    public Button AboutQuiz, UserTestScore,Back, Next;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    public int countOfCorrectAnswers = 0;
    public double grade=0;
    private Timeline timer;
    private long remainingTime;
    public int shotNumber = 0;
    private Student currentStudent;
    public int index;

    @Autowired
    public testController(TestService testService) {
        this.testService = testService;
    }
    public void resetQuiz(){
        currentQuestionIndex = 0;
        countOfCorrectAnswers = 0;
        grade=0;
        questions = questionService.getRandomQuestions(10);
        if (!questions.isEmpty()) {
            displayQuestion(questions.get(currentQuestionIndex));
        }
        startTimer();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shotNumber ++;
        System.out.println(shotNumber/2);
        questions = questionService.getRandomQuestions(10);
        if (!questions.isEmpty()) {
            displayQuestion(questions.get(currentQuestionIndex));
        }
        startTimer();
        currentStudent = studentService.getCurrentStudent();
        if (currentStudent != null) {
            System.out.println("Zalogowany student w testController: " + index);
        } else {
            System.out.println("Brak zalogowanego studenta w testController");
        }
    }

    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }
        remainingTime = 10 * 60;
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (remainingTime <= 0) {
                timer.stop();
                displayResult();
            } else {
                remainingTime--;
                long m = remainingTime / 60;
                long s = remainingTime % 60;
                Time.setText(String.format("%02d:%02d", m, s));
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.playFromStart();
    }

    public void BackButton(javafx.event.ActionEvent actionEvent){
        Student currentStudent = currentStudentService.getCurrentStudent();
        if (currentStudent.getRole() == Roles.STUDENT) {
            try {
                if (timer != null) {
                    timer.stop();
                }
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
        else {
            try {
                if (timer != null) {
                    timer.stop();
                }
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

    public void AboutQuizButton(javafx.event.ActionEvent actionEvent){
        try {
            Stage stage = (Stage) Next.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AboutQuiz.fxml"));
            fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UserTestScoreButton(javafx.event.ActionEvent actionEvent){
        try {
            Stage stage = (Stage) Next.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UserTestScore.fxml"));
            fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void NextButton(javafx.event.ActionEvent actionEvent){
        checkAnswers(questions.get(currentQuestionIndex));
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            displayQuestion(questions.get(currentQuestionIndex));
        }
        else{displayResult();}
        }

    private void displayResult(){
        try {
            if (timer != null) {
                timer.stop();
            }
            Stage stage = (Stage) Next.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/endQuiz.fxml"));
            fxmlLoader.setControllerFactory(JavaQuizzAppApplication.getSpringContext()::getBean);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

            calculateGrade(countOfCorrectAnswers,currentQuestionIndex);
            Grade.setText(String.valueOf(calculateGrade(countOfCorrectAnswers,currentQuestionIndex)));
            YourScore.setText(String.valueOf(countOfCorrectAnswers));
            MaxScore.setText(String.valueOf(currentQuestionIndex+1));

            String score = YourScore.getText();
            String max = MaxScore.getText();
            String grade = Grade.getText();
            int shot = shotNumber/2;

            Student currentStudent = currentStudentService.getCurrentStudent();
            Test test = new Test(null,shot,score,max,grade,currentStudent);
            test.setStudent(currentStudent);
            testService.saveTest(test);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void displayQuestion(Question question) {
        LabelQuestionNumber.setText(String.valueOf(currentQuestionIndex + 1));
        Question.setText(question.getQuestion());

        List<Answer> answers = question.getAnswers();
        if (!answers.isEmpty()) {
            Answer1.setText(answers.get(0).getAnswer());
            if (answers.size() > 1) Answer2.setText(answers.get(1).getAnswer());
            if (answers.size() > 2) Answer3.setText(answers.get(2).getAnswer());
            if (answers.size() > 3) Answer4.setText(answers.get(3).getAnswer());
        } else {
            Answer1.setText("");
            Answer2.setText("");
            Answer3.setText("");
            Answer4.setText("");
        }
        Answer1.setSelected(false);
        Answer2.setSelected(false);
        Answer3.setSelected(false);
        Answer4.setSelected(false);
    }
    public void checkAnswers(Question question){
        List<Answer> answers = question.getAnswers();

        boolean correct=true;
        if (!answers.isEmpty()) correct &= (answers.get(0).isCorrect() == Answer1.isSelected());
        if (answers.size() > 1) correct &= (answers.get(1).isCorrect() == Answer2.isSelected());
        if (answers.size() > 2) correct &= (answers.get(2).isCorrect() == Answer3.isSelected());
        if (answers.size() > 3) correct &= (answers.get(3).isCorrect() == Answer4.isSelected());

        if(correct){
            countOfCorrectAnswers++;
        }
    }

    public double calculateGrade(int score, int maxScore){
        double percent = ((double) score /maxScore)*100;
        double grade;

        if(percent>=90) grade = 5;
        else if(percent>=85 ) grade =4.5;
        else if(percent>=75 ) grade =4;
        else if(percent>=65 ) grade=3.5;
        else if(percent>=60 ) grade=3;
        else grade=2;

        return grade;
    }

}
