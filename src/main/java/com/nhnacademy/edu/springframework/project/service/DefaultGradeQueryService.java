package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.SQLClientInfoException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DefaultGradeQueryService implements GradeQueryService {

    private final Students students;
    private final Scores scores;

    public DefaultGradeQueryService(Students students, Scores scores) {
        this.students = students;
        this.scores = scores;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {


        return students.findAll().stream()
                .filter(student -> student.getName().equals(name))
                .map(Student::getScore)
                .collect(Collectors.toList());
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        Optional<Score> optionalScore = scores.findAll().stream()
                .filter(score -> score.getStudentSeq() == seq)
                .findAny();
        if (optionalScore.isPresent()) {
            return optionalScore.get();
        }else{
            throw new NoSuchElementException("해당하는 학번에 매칭되는 점수가 없습니다");
        }

    }
}
