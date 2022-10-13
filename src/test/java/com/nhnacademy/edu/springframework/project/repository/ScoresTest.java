package com.nhnacademy.edu.springframework.project.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    @Test
    void load() {
        Assertions.assertThatCode(() -> CsvScores.getInstance().load())
                .doesNotThrowAnyException();
    }

    @Test
    void findAll() {
        CsvScores.getInstance().load();
        List<Score> all = CsvScores.getInstance().findAll();
        Assertions.assertThat(all).isNotEmpty();
    }

    @Test
    void findAll_contentsCheck() {
        CsvScores.getInstance().load();
        List<Score> all = CsvScores.getInstance().findAll();
        Assertions.assertThat(all.get(0).getStudentSeq()).isEqualTo(1);
    }
}
