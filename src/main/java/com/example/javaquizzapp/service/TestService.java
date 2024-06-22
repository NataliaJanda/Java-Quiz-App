package com.example.javaquizzapp.service;

import com.example.javaquizzapp.entity.Test;
import com.example.javaquizzapp.repository.StudentRepository;
import com.example.javaquizzapp.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;
    public List<Test> findAllTest() {return testRepository.findAll();}

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test saveTest(Test test){
        return testRepository.save(test);
    }

}
