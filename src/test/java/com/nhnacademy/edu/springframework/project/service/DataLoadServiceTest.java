package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    @Test
    void loadAndMerge() {
        DataLoadService dataLoadService = new CsvDataLoadService();
        Assertions.assertThrows(InputMismatchException.class, dataLoadService::loadAndMerge);
    }
}
