package com.example.javaquizzapp.service;

import com.example.javaquizzapp.entity.Student;
import com.example.javaquizzapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student registerStudent(Student student) {
        //TODO:walidacja hasła
        return studentRepository.save(student);
    }

    public boolean validateStudent(String index, String password){
        Optional<Student> student = studentRepository.findByIndex(index);
        return student.isPresent() && student.get().getPassword().equals(password);
    }
}
