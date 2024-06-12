package com.example.javaquizzapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
    @Column(nullable = false)
    String index;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String lastName;
    @Column(nullable = false)
    String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Student(Long id,String index, String name, String lastName, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.index = index;
        this.password = password;
    }

    public Student() {
    }
}
