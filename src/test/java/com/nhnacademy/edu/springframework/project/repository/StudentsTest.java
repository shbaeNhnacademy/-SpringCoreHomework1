package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class StudentsTest {

    @Autowired
    private Students students;

    @Autowired
    private Scores scores;

    @AfterEach
    public void finished() {
        scores.clear();
        students.clear();
    }

    @Test
    void load() {
        Assertions.assertThatCode(() -> students.load())
                .doesNotThrowAnyException();
    }

    @Test
    void findAll() {
        students.load();
        Collection<Student> all = students.findAll();
        Assertions.assertThat(all).isNotEmpty();
    }

    @Test
    void findAll_contentsCheck() {
        students.load();
        List<Student> collect = new ArrayList<>(students.findAll());
        Assertions.assertThat(collect.get(0).getSeq()).isEqualTo(1);
    }

    @Test
    void merge() {
        students.load();
        scores.load();
        students.merge(scores.findAll());

        Collection<Student> all = students.findAll();
        List<Student> collect = new ArrayList<>(all);

        Assertions.assertThat(collect.get(0).getScore()).isNotNull();

    }
}
