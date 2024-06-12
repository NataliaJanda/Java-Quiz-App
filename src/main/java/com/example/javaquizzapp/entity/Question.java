package com.example.javaquizzapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Question {
    @Column (nullable = false)
    String question;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
