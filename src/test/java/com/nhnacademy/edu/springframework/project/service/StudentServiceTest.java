package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentServiceTest {
    StudentService studentService = new DefaultStudentService();
//    @BeforeEach
//    public void setup() {
//        CsvScores.getInstance().load();
//        CsvStudents.getInstance().load();
//
//    }
//
//    @AfterEach
//    public void finished() {
//        CsvScores.getInstance().clear();
//        CsvStudents.getInstance().clear();
//    }
    @Test
    void getPassedStudents() {
//        List<Student> studentList = new ArrayList<>();
//        studentList.add(new Student(1, "A"));
//
//        List<Score> scoreList  = new ArrayList<>();
//        scoreList.add(new Score(1, 90));
//
//        CsvScores csvScores = mock(CsvScores.class);
//        when(csvScores.findAll()).thenReturn(scoreList);
//
//        CsvStudents csvStudents = mock(CsvStudents.class);
//        when(csvStudents.findAll()).thenReturn(studentList);
//        csvStudents.merge(csvScores.findAll());

//        CsvStudents.getInstance().merge(CsvScores.getInstance().findAll());

        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            Collection<Student> passedStudents = studentService.getPassedStudents();
        });
//        Assertions.assertThat(passedStudents.isEmpty()).isFalse();
    }

    @Test
    void getStudentsOrderByScore() {
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            Collection<Student> passedStudents = studentService.getStudentsOrderByScore();
        });
    }
}
