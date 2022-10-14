package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.service.Student;
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
class ScoresTest {

    @Autowired
    private Scores scores;

    @AfterEach
    public void finished() {
        scores.clear();
    }

    @Test
    void load() {
        Assertions.assertThatCode(() -> scores.load())
                .doesNotThrowAnyException();
    }

    @Test
    void findAll() {
        scores.load();
        List<Score> all = scores.findAll();
        Assertions.assertThat(all).isNotEmpty();
    }

    @Test
    void findAll_contentsCheck() {
        scores.load();
        List<Score> all = scores.findAll();
        Assertions.assertThat(all.get(0).getStudentSeq()).isEqualTo(1);
    }
}
