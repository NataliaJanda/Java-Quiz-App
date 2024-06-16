package com.example.javaquizzapp.service;

import com.example.javaquizzapp.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class CurrentStudentService {

    private Student currentStudent;

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }
}
