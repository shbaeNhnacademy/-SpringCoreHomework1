package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class DataLoadServiceTest {

    @Autowired
    private Students students;

    @Autowired
    private Scores scores;

    @Autowired
    DataLoadService dataLoadService;

    @Test
    void loadAndMerge() {

        dataLoadService.loadAndMerge();
        List<Score> all = scores.findAll();
        org.assertj.core.api.Assertions.assertThat(all.get(0).getScore()).isEqualTo(30);

        Collection<Student> all1 = students.findAll();
        org.assertj.core.api.Assertions.assertThat(all1.isEmpty()).isFalse();
    }
}
