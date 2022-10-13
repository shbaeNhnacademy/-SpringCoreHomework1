package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeQueryServiceTest {

    GradeQueryService gradeQueryService = new DefaultGradeQueryService();
    @BeforeEach
    public void setup() {
        CsvScores.getInstance().load();
        CsvStudents.getInstance().load();
    }

    @AfterEach
    public void finished() {
        CsvScores.getInstance().clear();
        CsvStudents.getInstance().clear();
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
