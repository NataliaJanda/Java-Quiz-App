package com.example.javaquizzapp.service;

import com.example.javaquizzapp.FXcontroller.testController;
import com.example.javaquizzapp.entity.Student;
import com.example.javaquizzapp.repository.StudentRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CurrentStudentService currentStudentService;

    @Getter
    public Student currentStudent;

    @Autowired
    @Lazy
    public testController testController;

    @Autowired
    public StudentService(StudentRepository studentRepository, CurrentStudentService currentStudentService) {
        this.studentRepository = studentRepository;
        this.currentStudentService = currentStudentService;
    }

    public void registerStudent(Student student) {
        //TODO:walidacja hasła
        studentRepository.save(student);
        currentStudentService.setCurrentStudent(student);

    }

    public boolean validateStudent(String index, String password) {
        Optional<Student> studentOptional = studentRepository.findByIndexAndPassword(index, password);
        if (studentOptional.isPresent()) {
            Student currentStudent = studentOptional.get();
            int i = Integer.parseInt(currentStudent.getIndex());
            testController.getCurrentIndex(i);
            currentStudentService.setCurrentStudent(currentStudent);

            System.out.println("Zalogowany student: " + i + " " + currentStudent.getName());
            return true;
        }
        return false;
    }
}
