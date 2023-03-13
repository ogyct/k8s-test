package com.example.k8stest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final TestEntityRepository testEntityRepository;

    private static final Logger log = LoggerFactory.getLogger(TestService.class);

    public TestService(TestEntityRepository testEntityRepository) {
        this.testEntityRepository = testEntityRepository;
    }

    public String testMethod() {
        return testEntityRepository.findAll().get(0).getName();
    }
}
