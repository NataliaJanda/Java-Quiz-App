package com.example.javaquizzapp.FXcontroller;

import com.example.javaquizzapp.JavaQuizzAppApplication;
import com.example.javaquizzapp.entity.Answer;
import com.example.javaquizzapp.entity.Question;
import com.example.javaquizzapp.service.QuestionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class AddQuestionController implements Initializable {
    public Button Back;
    public TextField answer1Field,answer2Field,answer3Field,answer4Field;
    public ChoiceBox<String> ChoiceBoxAns2,ChoiceBoxAns1,ChoiceBoxAns3,ChoiceBoxAns4;
    public Label InfoLabel;

    public Button selectImageButton;
    private FileChooser imageFileChooser;
    private File selectedImageFile;
    private final String[] PF = {"prawda","fałsz"};
    public TextArea questionField;
    public Label imagePathLabel;

    public Button AddingQuestion;
    @Autowired
    private QuestionService questionService;
    public void initialize(URL arg0, ResourceBundle arg1){
        ChoiceBoxAns1.getItems().addAll(PF);
        ChoiceBoxAns2.getItems().addAll(PF);
        ChoiceBoxAns3.getItems().addAll(PF);
        ChoiceBoxAns4.getItems().addAll(PF);
        imageFileChooser = new FileChooser();
        imageFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
    }
    public void selectImageFile(ActionEvent actionEvent) {
        selectedImageFile = imageFileChooser.showOpenDialog(null);
        if (selectedImageFile != null) {
            imagePathLabel.setText(selectedImageFile.getName());
        } else {
            imagePathLabel.setText("");
        }
    }
    public void AddingQuestionButton(ActionEvent actionEvent) {
        try {
            String questionText = questionField.getText();
            Question question = new Question();
            question.setQuestion(questionText);

            if (selectedImageFile != null) {
                String base64Image = encodeFileToBase64(selectedImageFile);
                question.setImageData(base64Image);
            }

            List<Answer> answers = new ArrayList<>();
            answers.add(new Answer(null, answer1Field.getText(), ChoiceBoxAns1.getValue().equals("prawda"), question));
            answers.add(new Answer(null, answer2Field.getText(), ChoiceBoxAns2.getValue().equals("prawda"), question));
            answers.add(new Answer(null, answer3Field.getText(), ChoiceBoxAns3.getValue().equals("prawda"), question));
            answers.add(new Answer(null, answer4Field.getText(), ChoiceBoxAns4.getValue().equals("prawda"), question));

            question.setAnswers(answers);

            questionService.saveQuestion(question);

            InfoLabel.setText("Pytanie zostało dodane pomyślnie!");

            questionField.clear();
            answer1Field.clear();
            answer2Field.clear();
            answer3Field.clear();
            answer4Field.clear();
            ChoiceBoxAns1.setValue(null);
            ChoiceBoxAns2.setValue(null);
            ChoiceBoxAns3.setValue(null);
            ChoiceBoxAns4.setValue(null);
            selectedImageFile = null;
            imagePathLabel = null;
            InfoLabel = null;

        } catch (Exception e) {
            InfoLabel.setText("Wystąpił błąd podczas dodawania pytania.");
            e.printStackTrace();
        }
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
    private String encodeFileToBase64(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] fileBytes = IOUtils.toByteArray(fileInputStream);
            return Base64.getEncoder().encodeToString(fileBytes);
        }
    }

}
