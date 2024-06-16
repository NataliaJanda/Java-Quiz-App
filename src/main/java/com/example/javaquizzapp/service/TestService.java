package com.example.javaquizzapp.service;

import com.example.javaquizzapp.entity.Test;
import com.example.javaquizzapp.repository.StudentRepository;
import com.example.javaquizzapp.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public TestService(TestRepository testRepository, StudentRepository studentRepository) {
        this.testRepository = testRepository;
        this.studentRepository = studentRepository;
    }

    public Test saveTest(Test test){
        return testRepository.save(test);
    }
}
