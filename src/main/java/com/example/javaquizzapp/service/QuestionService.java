package com.example.javaquizzapp.service;

import com.example.javaquizzapp.entity.Question;
import com.example.javaquizzapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public List<Question> getAllQuestionsWithAnswers() {
        List<Question> questions = questionRepository.findAll();
        for (Question question : questions) {
            question.getAnswers().size(); // Wymuszenie ładowania kolekcji
        }
        return questions;
    }
}
