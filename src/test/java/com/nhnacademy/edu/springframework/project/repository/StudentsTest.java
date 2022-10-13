package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {

    @Test
    void load() {
        Assertions.assertThatCode(() -> CsvStudents.getInstance().load())
                .doesNotThrowAnyException();
    }

    @Test
    void findAll() {
        CsvStudents.getInstance().load();
        Collection<Student> all = CsvStudents.getInstance().findAll();
        Assertions.assertThat(all).isNotEmpty();
    }

    @Test
    void findAll_contentsCheck() {
        CsvStudents.getInstance().load();
        List<Student> collect = new ArrayList<>(CsvStudents.getInstance().findAll());
        Assertions.assertThat(collect.get(0).getSeq()).isEqualTo(1);
    }

    @Test
    void merge_fail() {
        CsvStudents.getInstance().load();
        CsvScores.getInstance().load();
        org.junit.jupiter.api.Assertions.assertThrows(InputMismatchException.class, () -> {
            CsvStudents.getInstance().merge(CsvScores.getInstance().findAll());
        });


    }
}
