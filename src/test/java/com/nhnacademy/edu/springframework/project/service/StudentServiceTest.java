package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.repository.*;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class StudentServiceTest {

    @Autowired
    private Students students;

    @Autowired
    private Scores scores;

    @Autowired
    StudentService studentService;

    @Autowired
    DataLoadService dataLoadService;
    @BeforeEach
    public void setup() {
        dataLoadService.loadAndMerge();
    }

    @AfterEach
    public void finished() {
        scores.clear();
        students.clear();
    }
    @Test
    void getPassedStudents() {
        Collection<Student> passedStudents = studentService.getPassedStudents();
        Assertions.assertThat(passedStudents.isEmpty()).isFalse();
    }

    @Test
    void getPassedStudents_contentsCheck() {
        Collection<Student> passedStudents = studentService.getPassedStudents();
        List<Student> collect = new ArrayList<>(passedStudents);

        Condition<Student> condition =
                new Condition<>(s-> !s.getScore().isFail(),"pass");

        Assertions.assertThat(collect.get(0)).is(condition);
    }

    @Test
    void getStudentsOrderByScore() {
        Collection<Student> studentsOrderByScore = studentService.getStudentsOrderByScore();
        Assertions.assertThat(studentsOrderByScore.isEmpty()).isFalse();

    }

    @Test
    void getStudentsOrderByScore_contentsCheck() {
        Collection<Student> studentsOrderByScore = studentService.getStudentsOrderByScore();
        List<Student> collect = new ArrayList<>(studentsOrderByScore);

//        Condition<Student> condition =
//                new Condition<>(s-> s.getScore().getScore(),"pass");

        Assertions.assertThat(collect)
                .isSortedAccordingTo(Comparator.comparing(student -> student.getScore().getScore()));
    }
}
