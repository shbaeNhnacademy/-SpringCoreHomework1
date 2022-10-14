package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    @Test
    void loadAndMerge() {
        DataLoadService dataLoadService = new CsvDataLoadService();
//        Assertions.assertThrows(InputMismatchException.class, dataLoadService::loadAndMerge);
        dataLoadService.loadAndMerge();
        List<Score> all = CsvScores.getInstance().findAll();
        org.assertj.core.api.Assertions.assertThat(all.get(0).getScore()).isEqualTo(30);

        Collection<Student> all1 = CsvStudents.getInstance().findAll();
        org.assertj.core.api.Assertions.assertThat(all1.isEmpty()).isFalse();
    }
}
