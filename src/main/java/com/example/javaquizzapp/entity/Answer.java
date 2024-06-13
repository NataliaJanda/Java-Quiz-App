package com.example.javaquizzapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public Answer(){}

    public Answer(Long id, String answer, boolean correct, Question question){
        this.id = id;
        this.answer = answer;
        this.correct = correct;
        this.question = question;
    }
}
