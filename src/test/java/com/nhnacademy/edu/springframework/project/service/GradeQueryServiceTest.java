package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class GradeQueryServiceTest {

    @Autowired
    private Students students;

    @Autowired
    private Scores scores;

    @Autowired
    GradeQueryService gradeQueryService;
    @BeforeEach
    public void setup() {
        scores.load();
        students.load();
    }

    @AfterEach
    public void finished() {
        scores.clear();
        students.clear();
    }
    @Test
    void getScoreByStudentName() {

        List<Score> scoreByStudentName = gradeQueryService.getScoreByStudentName("A");
        Assertions.assertThat(scoreByStudentName.size()).isEqualTo(2);
    }

    @Test
    void getScoreByStudentSeq() {
        Score scoreByStudentSeq = gradeQueryService.getScoreByStudentSeq(1);
        Assertions.assertThat(scoreByStudentSeq.getScore()).isEqualTo(30);
    }
}
