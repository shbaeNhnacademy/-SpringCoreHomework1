package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultStudentService implements StudentService {

    private final Students students;

    public DefaultStudentService(Students students) {
        this.students = students;
    }

    @Override
    public Collection<Student> getPassedStudents() {

        return students.findAll().stream()
                .filter(student -> !(student.getScore().isFail()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {

        return students.findAll().stream()
                .sorted(Comparator.comparing(student -> student.getScore().getScore()))
                .collect(Collectors.toList());
    }

}
