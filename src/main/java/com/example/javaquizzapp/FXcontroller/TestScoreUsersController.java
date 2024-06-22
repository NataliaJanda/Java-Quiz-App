package com.example.javaquizzapp.FXcontroller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import com.example.javaquizzapp.entity.Test;
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
public class TestScoreUsersController {
    @Autowired
    private TestService testService;
    public Button Back;
    public TableColumn<Test,String> index;
    public TableColumn<Test,String> score;
    public TableColumn<Test,String> maxScore;
    public TableColumn<Test,String> grade;

    @FXML
    private TableView<Test> testTable;
    @FXML
    public void initialize(){
        index.setCellValueFactory(new PropertyValueFactory<>("studentIndex"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        maxScore.setCellValueFactory(new PropertyValueFactory<>("maxScore"));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        List<Test> test = testService.findAllTest();
        ObservableList<Test> testObservableList = FXCollections.observableArrayList(test);
        testTable.setItems(testObservableList);
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
