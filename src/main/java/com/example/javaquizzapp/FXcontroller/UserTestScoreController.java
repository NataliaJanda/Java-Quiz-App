package com.example.javaquizzapp.FXcontroller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import com.example.javaquizzapp.entity.Student;
import com.example.javaquizzapp.entity.Test;
import com.example.javaquizzapp.service.CurrentStudentService;
import com.example.javaquizzapp.service.TestService;
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
public class UserTestScoreController {
    @Autowired
    private CurrentStudentService currentStudentService;
    @Autowired
    private TestService testService;
    public Button Back;
    public TableColumn<Test,String> test_id;
    public TableColumn<Test,String> YourScore;
    public TableColumn<Test,String> MaxScore;
    public TableColumn<Test,String> Grade;

    @FXML
    private TableView<Test> UserScoreTable;
    @FXML
    public void initialize(){
        test_id.setCellValueFactory(new PropertyValueFactory<>("studentIndex"));
        YourScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        MaxScore.setCellValueFactory(new PropertyValueFactory<>("maxScore"));
        Grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        Student currentStudent = currentStudentService.getCurrentStudent();

        List<Test> test = testService.findAllTestsByStudent(currentStudent.getId());
        ObservableList<Test> testObservableList = FXCollections.observableArrayList(test);
        UserScoreTable.setItems(testObservableList);
    }

    public void BackButton(ActionEvent actionEvent) {
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
}
